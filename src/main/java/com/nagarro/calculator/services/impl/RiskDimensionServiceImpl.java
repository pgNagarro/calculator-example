package com.nagarro.calculator.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.enums.JobStatus;
import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.repositories.JobRepository;
import com.nagarro.calculator.repositories.RiskDimensionRepository;
import com.nagarro.calculator.services.RiskDimensionService;

/**
 * Service Implementation Class for Risk Dimension Service
 * @author parasgautam
 *
 */
@Service
public class RiskDimensionServiceImpl implements RiskDimensionService{
	
	private static final Logger logger = LoggerFactory.getLogger(RiskDimensionServiceImpl.class);

	@Autowired
	private RiskDimensionRepository riskDimensionRepository;
	
	@Autowired
	private CompanyRiskScoreRepository companyRiskScoreRepository;
	
	@Autowired 
	private JobRepository jobRepository;


	/**
	 * Method to get risk dimension data
	 */
	@Override
	public List<RiskDimension> getAllRiskDimension() {
		
		logger.info("start : getAllRiskDimension");
		return riskDimensionRepository.findAll();
		
	}

	/**
	 * Method to save risk dimension data
	 * @throws IOException 
	 */
	@Override
	public RiskDimension saveRiskDimension(RiskDimension riskDimension) throws IOException {
		
		logger.info("start : saveRiskDimension");
		
		List<RiskDimension> riskDimensionList = riskDimensionRepository.findAll();
		
		long sum = 0;
		for(RiskDimension ele : riskDimensionList) {
			sum+=ele.getWeight();
		}
		sum+=riskDimension.getWeight();
		
		if(sum<100 || sum>100) {
			
			ResultServiceImpl.job.setJobStatus(JobStatus.FAILED);
			ResultServiceImpl.job.setDate(new Date());
			ResultServiceImpl.job.setDesc("Invalid Value. Sum of all individual weight should be equal to 100%");
			jobRepository.save(ResultServiceImpl.job);
			
			throw new IOException("Invalid Value. Sum of all individual weight should be equal to 100%");
			
		}
		
		addDimensionToCompanyRiskScore(riskDimension);
		
		return riskDimensionRepository.save(riskDimension);
		
	}
	
	/**
	 * Method to get risk dimension data by Id
	 * @throws IOException 
	 */
	@Override
	public RiskDimension getRiskDimensionById(String dimension) throws IOException {
		
		logger.info("start : getRiskDimensionById");
		
		RiskDimension riskDimensions = riskDimensionRepository.findByDimension(dimension);
		
		if(riskDimensions==null) {
			throw new IOException("Risk dimension not present");
		}
		return riskDimensions;
		
	}
	
	/**
	 * Method to delete risk dimension data
	 */
	@Override
	public void deleteRiskDimension(RiskDimension riskDimension) {
		
		logger.info("start : getRiskDimensionById");
		riskDimensionRepository.deleteById(riskDimension.getDimension());
		
	}
	
	/**
	 * Method to add dimension to company risk score data when new dimension is added
	 * @param riskDimension
	 */
	void addDimensionToCompanyRiskScore(RiskDimension riskDimension) {
		
		List<CompanyRiskScore> riskScoreList = companyRiskScoreRepository.findAll();

		for(CompanyRiskScore companyRiskScore:riskScoreList) {
			
			Dimensions dimension = new Dimensions();
			dimension.setDimensionName(riskDimension.getDimension());
			dimension.setDimensionValue(0);
	
			companyRiskScore.getDimensions().add(dimension);
		}
		
	}
	

}
