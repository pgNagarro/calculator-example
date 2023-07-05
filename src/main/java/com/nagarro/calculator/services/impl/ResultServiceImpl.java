package com.nagarro.calculator.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.nagarro.calculator.enums.JobStatus;
import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Job;
import com.nagarro.calculator.models.OutputValues;
import com.nagarro.calculator.models.Result;
import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.repositories.ResultRepository;
import com.nagarro.calculator.services.CompanyRiskScoreService;
import com.nagarro.calculator.services.JobService;
import com.nagarro.calculator.services.ResultService;
import com.nagarro.calculator.services.RiskCalcService;
import com.nagarro.calculator.services.RiskDimensionService;
import com.nagarro.calculator.services.RiskScoreLevelService;
import com.nagarro.calculator.services.ScoreCapService;

/**
 * Service Implementation Class for Result Service
 * @author parasgautam
 *
 */
@Service
public class ResultServiceImpl implements ResultService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;

	@Autowired
	private RiskCalcService riskCalcService;

	@Autowired
	private RiskDimensionService riskDimensionService;

	@Autowired
	private RiskScoreLevelService riskScoreLevelService;

	@Autowired
	private ScoreCapService scoreCapService;
	
	@Autowired
	private JobService jobService;

	@Override
	public void addResult(Result result) {
		resultRepository.save(result);
	}

	@Override
//	@Scheduled(fixedRate = 10000)
	public void calculateResult() {
		
		Job job = new Job();
		
		logger.info("start : calculateResult");

		try {
			
			List<CompanyRiskScore> riskScoreList = companyRiskScoreService.getAllCompanyRiskScore();
			List<RiskCalc> riskCalcList = riskCalcService.getAllRiskCalcLogic();
			List<RiskDimension> riskDimensionList = riskDimensionService.getAllRiskDimension();
			Map<String, Integer> totalCappedScoreMap = calculateTotalRiskedCappedScore();
			String operations = "[(),%/*+-]";
			
			if( riskScoreList.isEmpty() || riskCalcList.isEmpty() || riskDimensionList.isEmpty() || totalCappedScoreMap.isEmpty()) {
				logger.error("One or multiple tables are empty in database");
				throw new IOException("One or multiple tables are empty in database");
			}
	
			for (int m = 0; m < riskScoreList.size(); m++) { // for each company and for its values
				
				Map<String, Integer> elementResultMap = new HashMap<>(); // stores formula element name and its
																			// corresponding resulting value

				elementResultMap.put("total_risk_capped_score",
						totalCappedScoreMap.get(riskScoreList.get(m).getCompanyName()));
				
				for (int i = 0; i < 10; i++) { // for evaluating values if missing then skipping					
					evaluateFormula(riskScoreList,riskCalcList,riskDimensionList,elementResultMap,operations,m);
				} // 10
				
				logger.info(riskScoreList.get(m).getCompanyName() + " " + elementResultMap);
				
				insertValuesInResultTable(elementResultMap, riskScoreList, m);
				job.setDate(new Date());
				job.setJobStatus(JobStatus.SUCCESSFULL);
				job.setDesc("Job executed with no errors");
				jobService.addJob(job);

			}
		} catch (Exception e) {
			job.setDesc(e.toString());
			job.setDate(new Date());
			job.setJobStatus(JobStatus.FAILED);
			jobService.addJob(job);
			e.printStackTrace();
		}

	}

	public void evaluateFormula(List<CompanyRiskScore> riskScoreList,List<RiskCalc> riskCalcList,List<RiskDimension> riskDimensionList, Map<String, Integer> elementResultMap, String operations,int m) throws IOException{
		
		for (int j = 0; j < riskCalcList.size(); j++) {

			String elementName = riskCalcList.get(j).getElementName();
			String formula = riskCalcList.get(j).getFormula();

			String[] formula_array = formula.split(operations);
			
			int formulaResultValue = -1;
			int flag = -1;

			DoubleEvaluator eval = new DoubleEvaluator();
			StaticVariableSet<Double> variables = new StaticVariableSet<Double>();

			if (formula_array[0].equals("min") || formula_array[0].equals("max")) {
				// check in dimension table and check in elementResultMap		
				int value1=0;
				int value2=0;
				for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
					if (formula_array[1]
							.equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
						value1 = riskScoreList.get(m).getDimensions().get(l).getDimensionvalue();
						break;
					}
				}
				if(elementResultMap.containsKey(formula_array[1])){
					value1=elementResultMap.get(formula_array[1]);
				}
				else {
					continue;
				}
				
				for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
					if (formula_array[2]
							.equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
						value2 = riskScoreList.get(m).getDimensions().get(l).getDimensionvalue();
						break;
					}
				}
				if(elementResultMap.containsKey(formula_array[2])){
					value2=elementResultMap.get(formula_array[2]);
				}
				else {
					continue;
				}
				if(formula_array[0].equals("min")) {
					formulaResultValue = Math.min(value1,value2);
				}
				if(formula_array[0].equals("max")) {
					formulaResultValue = Math.max(value1,value2);
				}	
				elementResultMap.put(elementName,formulaResultValue);
	
			} else {
				for (int k = 0; k < formula_array.length; k++) {
					// check if value is present or not in dimension table
					if (elementResultMap.containsKey(formula_array[k])) {
						variables.set(formula_array[k], Double.valueOf(elementResultMap.get(formula_array[k])));
					} else {

						for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
							if (formula_array[k]
									.equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
								variables.set(formula_array[k], Double.valueOf(
										riskScoreList.get(m).getDimensions().get(l).getDimensionvalue()));
								flag = 0;
								break;
							}
						}

						for (int p = 0; p < riskDimensionList.size(); p++) {
							if (formula_array[k].equals(riskDimensionList.get(p).getDimension())) {
								variables.set("weight",
										Double.valueOf(riskDimensionList.get(p).getWeight() * 0.01f));
								flag = 0;
								break;
							}
						}
					}
				}
			}
			if (formulaResultValue == -1 || flag == 1) {
				Double result = eval.evaluate(formula, variables);
				elementResultMap.put(elementName, result.intValue());
			}
		} 
	}

	
	// Method to insert calculated values from risk calc logic to output table
		public void insertValuesInResultTable(Map<String, Integer> elementResultMap, List<CompanyRiskScore> riskScoreList,
				int companyRiskScore) {
			Result result = new Result();
			List<OutputValues> outputValuesList = new ArrayList<>();
			result.setCompanyName(riskScoreList.get(companyRiskScore).getCompanyName());
			result.setTotalRiskCappedScore(elementResultMap.get("total_risk_capped_score"));

			for (Map.Entry<String, Integer> entry : elementResultMap.entrySet()) {
			//	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				OutputValues outputValues = new OutputValues(entry.getKey(), entry.getValue());
				outputValuesList.add(outputValues);
			}
			result.setValues(outputValuesList);
			addResult(result);
		}
		
		
	// Method to calculate total risk capped score
	public Map<String, Integer> calculateTotalRiskedCappedScore() throws IOException{
		
		logger.info("start : calculateTotalRiskedCappedScore");
		
		Map<String, Integer> map = new HashMap<>();
		String[] single_digits = new String[] { "zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
				"Nine" };

		List<CompanyRiskScore> riskScoreList = companyRiskScoreService.getAllCompanyRiskScore();
		List<RiskScoreLevel> riskScoreLevelList = riskScoreLevelService.getAllRiskScoreLevel();
		
//		if( riskScoreList.isEmpty() || riskScoreLevelList.isEmpty() ) {
//			logger.error("company_risk_score table or risk_score_level table is empty in database");
//			throw new IOException("One or multiple tables are empty in database");
//		}

		for (int i = 0; i < riskScoreList.size(); i++) {

			int totalRiskCappedScore = 100;
			Map<String, Integer> level = new HashMap<>();

			for (int j = 0; j < riskScoreList.get(i).getDimensions().size(); j++) {
				List<Integer> values = new ArrayList<>();
				values.add(riskScoreList.get(i).getDimensions().get(j).getDimensionvalue());
				compareScore(level,riskScoreLevelList,values,j);
			}
			for (Map.Entry<String, Integer> entry : level.entrySet()) {
				String s = single_digits[entry.getValue()] + " \"" + entry.getKey() + "\"";
				ScoreCap scoreCap = scoreCapService.findScoreCap(s);
				if (scoreCap != null) {
					totalRiskCappedScore = scoreCap.getTotalRiskCappedScore();
					break;
				}
			}
			map.put(riskScoreList.get(i).getCompanyName(), totalRiskCappedScore);
		}
		return map;
	}
	
	// Method to get score level from risk score level table for each value of dimension
	public void compareScore(Map<String,Integer> level,List<RiskScoreLevel> riskScoreLevelList, List<Integer> values, int j) {
		for (int k = 0; k < values.size(); k++) {
			for (int l = 0; l < riskScoreLevelList.size(); l++) {
				String[] scores = riskScoreLevelList.get(l).getScore().split("-");
				if (values.get(k) >= Integer.parseInt(scores[0])
						&& values.get(k) <= Integer.parseInt(scores[1])) {
					level.put(riskScoreLevelList.get(l).getLevel(),
							level.getOrDefault(riskScoreLevelList.get(l).getLevel(), 0) + 1);
					break;
				}
			}
		}
	}

	@Override
	public List<Result> getResult() {
		return resultRepository.findAll();
	}
	
}
