package com.nagarro.calculator.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.RiskDimension;
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

	/**
	 * Method to add risk dimension data
	 */
	@Override
	public void addRiskDimension(RiskDimension riskDimension) {
		
		logger.info("start : addRiskDimension");
		riskDimensionRepository.save(riskDimension);
		
	}

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
	 */
	@Override
	public RiskDimension saveRiskDimension(RiskDimension riskDimension) {
		
		logger.info("start : saveRiskDimension");
		return riskDimensionRepository.save(riskDimension);
		
	}
	
	/**
	 * Method to get risk dimension data by Id
	 * @throws IOException 
	 */
	@Override
	public RiskDimension getRiskDimensionById(String dimension) throws IOException {
		
		logger.info("start : getRiskDimensionById")
		;
		List<RiskDimension> riskDimensions = riskDimensionRepository.findByDimension(dimension);
		
		if(riskDimensions.isEmpty()) {
			throw new IOException("Risk dimension not present");
		}
		return riskDimensions.get(0);
		
	}
	
	/**
	 * Method to delete risk dimension data
	 */
	@Override
	public void deleteRiskDimension(RiskDimension riskDimension) {
		
		logger.info("start : getRiskDimensionById");
		riskDimensionRepository.deleteById(riskDimension.getDimension());
		
	}
	

}
