package com.nagarro.calculator.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.repositories.ResultRepository;
import com.nagarro.calculator.repositories.RiskDimensionRepository;
import com.nagarro.calculator.services.CompanyRiskScoreService;


/**
 * Service Implementation Class for Company Risk Score Service
 * @author parasgautam
 *
 */
@Service
public class CompanyRiskScoreServiceImpl implements CompanyRiskScoreService{

	private static final Logger logger = LoggerFactory.getLogger(CompanyRiskScoreServiceImpl.class);
	
	@Autowired
	private CompanyRiskScoreRepository companyRiskScoreRepository;
	
	@Autowired 
	private RiskDimensionRepository riskDimensionRepository;
	
	@Autowired
	private ResultRepository resultRepository;

	/**
	 *  Method for getting all company risk score data
	 */
	@Override
	public List<CompanyRiskScore> getAllCompanyRiskScore() {
		
		logger.info("start : getAllCompanyRiskScore");
		
		return companyRiskScoreRepository.findAll();
		
	}
	
	/**
	 * Method for checking if company risk score data is present or not 
	 */
	@Override
	public boolean checkDataIfPresent(CompanyRiskScore companyRiskScore) {
		
		logger.info("start : checkDataIfPresent");
		List<CompanyRiskScore> companyRiskScores = companyRiskScoreRepository.findByCompanyName(companyRiskScore.getCompanyName());
		return companyRiskScores.isEmpty();
		
	}
	
	/**
	 * Method to add company risk score data to repo
	 */
	@Override
	public void saveRiskScore(CompanyRiskScore companyRiskScore) throws IOException {
		
		logger.info("start : saveCompanyRiskScore");
		
		List<CompanyRiskScore> riskScores = companyRiskScoreRepository.findAll();
			
		try {	
		// Condition to check if data is already present or not
		if(!checkDataIfPresent(companyRiskScore)) {
			
			// find the risk score which is present and then add dimension
			for(CompanyRiskScore riskScore:riskScores) {
				
				if(riskScore.getCompanyName().equals(companyRiskScore.getCompanyName())) { 			// check in list of company is present
					
					int flag=-1;
					for(int i=0;i<riskScore.getDimensions().size();i++) {  							//check dimension if present or not
						
						if(riskScore.getDimensions().get(i).getDimensionName().equals(companyRiskScore.getDimensions().get(0).getDimensionName())) {
							
							flag = 0;
							throw new IOException("Dimension already present. Enter new dimension or update data");
							
						}
					}
					
					String dname = companyRiskScore.getDimensions().get(0).getDimensionName();
					int dvalue = companyRiskScore.getDimensions().get(0).getDimensionValue();
				
					if(flag==-1) {
						
						Dimensions dimension =new Dimensions(dname,dvalue);
						riskScore.getDimensions().add(dimension);
						
						companyRiskScoreRepository.save(riskScore);
						
						addDimensiontoRestCompanies(riskScore.getCompanyName(), dimension);
						
						break;
						
					}
				}
			}
		}
		else {
			if(riskScores.isEmpty()) {
				
				List<RiskDimension> riskDimensions = riskDimensionRepository.findAll();
				
				CompanyRiskScore newRiskScore = new CompanyRiskScore();
				newRiskScore.setCompanyName(companyRiskScore.getCompanyName());
				
				List<Dimensions> dimensions = new ArrayList<>();
				
				for(RiskDimension riskDimension:riskDimensions) {
					
					dimensions.add(new Dimensions(riskDimension.getDimension(),0));
					
				}
				
				newRiskScore.setDimensions(dimensions);
				
				for(RiskDimension riskDimension:riskDimensions) {
					
					if(riskDimension.getDimension().equals(companyRiskScore.getDimensions().get(0).getDimensionName())) {
						throw new IOException("Dimension already present.Enter new dimension or update data");				
					}
					
				}
				
				companyRiskScoreRepository.save(newRiskScore);
			}
			else {
				
				//compare dimension with present dimensions
				CompanyRiskScore newRiskScore = new CompanyRiskScore();
				newRiskScore.setCompanyName(companyRiskScore.getCompanyName());
				
				List<Dimensions> dimensions = new ArrayList<>();
				
				int flag=-1;
				
				for(Dimensions dimension:riskScores.get(0).getDimensions()) {
					
					if(companyRiskScore.getDimensions().get(0).getDimensionName().equals(dimension.getDimensionName())) {
						
						dimensions.add(new Dimensions(dimension.getDimensionName(),companyRiskScore.getDimensions().get(0).getDimensionValue()));
						flag=0;
						
					}
					else {
						
						dimensions.add(new Dimensions(dimension.getDimensionName(),0));
						
					}	
				}
				if(flag==-1) {
					
					String dname = companyRiskScore.getDimensions().get(0).getDimensionName();
					int dvalue = companyRiskScore.getDimensions().get(0).getDimensionValue();
					
					for(CompanyRiskScore riskScore:riskScores) {
						
						if(!(riskScore.getCompanyName().equals(companyRiskScore.getCompanyName()))) { // check in list of company is present
							
							Dimensions dimension =new Dimensions(dname,0);
							riskScore.getDimensions().add(dimension);
							
							companyRiskScoreRepository.save(riskScore);	
							
						}
					}
					
					dimensions.add(new Dimensions(dname,dvalue));
				}
				
				newRiskScore.setDimensions(dimensions);
				companyRiskScoreRepository.save(newRiskScore);		
				
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to get company risk score by company name
	 * @throws IOException 
	 */
	@Override
	public CompanyRiskScore getCompanyRiskScoreByCompanyName(String companyName) throws IOException {
		
		logger.info("start : getCompanyRiskScoreByName");
		
		List<CompanyRiskScore> companyRiskScores = companyRiskScoreRepository.findByCompanyName(companyName);
		
		if(companyRiskScores.isEmpty()) {
			throw new IOException("Company data not present");
		}
		
		return companyRiskScores.get(0);
	}
	
	/**
	 * Method to update company risk score data
	 */
	@Override
	public CompanyRiskScore updateRiskScore(CompanyRiskScore companyRiskScore) {
		
		logger.info("start : updateCompanyRiskScore");
		
		return companyRiskScoreRepository.save(companyRiskScore);	
	}
	
	/**
	 * Method to delete company risk score data
	 */
	@Override
	public void deleteCompanyRiskScore(CompanyRiskScore companyRiskScore) {
		
		logger.info("start : deleteCompanyRiskScore");
		
		companyRiskScoreRepository.deleteById(companyRiskScore.getId());
		
		resultRepository.deleteById(companyRiskScore.getCompanyName());
		
	}
	
	void addDimensiontoRestCompanies(String companyName,Dimensions dimension) {
		
		List<CompanyRiskScore> riskScoreList = companyRiskScoreRepository.findAll();
		
		for( CompanyRiskScore riskScore : riskScoreList ) {
			
			if(!(riskScore.getCompanyName().equals(companyName))) {
				
				Dimensions newdimension =new Dimensions(dimension.getDimensionName(),0);
				riskScore.getDimensions().add(newdimension);
				companyRiskScoreRepository.save(riskScore);
				
			}
			
		}
	}
	
}
