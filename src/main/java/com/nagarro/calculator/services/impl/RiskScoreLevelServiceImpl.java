package com.nagarro.calculator.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.repositories.RiskScoreLevelRepository;
import com.nagarro.calculator.services.RiskScoreLevelService;

/**
 * Service Implementation Class for Risk Score Level Service
 * @author parasgautam
 *
 */
@Service
public class RiskScoreLevelServiceImpl implements RiskScoreLevelService{

	private static final Logger logger = LoggerFactory.getLogger(RiskScoreLevelServiceImpl.class);
	
	
	@Autowired
	private RiskScoreLevelRepository riskScoreLevelRepository;
	

	/**
	 * Method to get risk score level data 
	 */
	@Override
	public List<RiskScoreLevel> getAllRiskScoreLevel() {
		
		logger.info("start : getAllRiskScoreLevel");
		return riskScoreLevelRepository.findAll();
		
	}
	
	/**
	 * Method to save risk score level data
	 */
	@Override
	public RiskScoreLevel saveRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
		
		logger.info("start : saveRiskScoreLevel");
		return riskScoreLevelRepository.save(riskScoreLevel);
		
	}
	
	/**
	 * Method to get risk score level data by score 
	 * @throws IOException 
	 */
	@Override
	public RiskScoreLevel getRiskScoreLevelByScore(String score) throws IOException {
		
		logger.info("start : getRiskScoreLevelByScore");
		
		RiskScoreLevel riskScoreLevels = riskScoreLevelRepository.findByScore(score);
		
		if(riskScoreLevels==null) {
			throw new IOException("Risk Score Level not found");
		}
		
		return riskScoreLevels;
		
	}
	
	/**
	 * Method to delete risk score level data
	 */
	@Override
	public void deleteRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
		
		logger.info("start : deleteRiskScoreLevel");
		riskScoreLevelRepository.deleteById(riskScoreLevel.getScore());	
		
	}
}
