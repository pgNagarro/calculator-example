package com.nagarro.calculator.services.impl;

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

	@Override
	public void addRiskDimension(RiskDimension riskDimension) {
		logger.info("start : addRiskDimension");
		riskDimensionRepository.save(riskDimension);
	}

	@Override
	public List<RiskDimension> getAllRiskDimension() {
		logger.info("start : getAllRiskDimension");
		return riskDimensionRepository.findAll();
	}

	@Override
	public RiskDimension saveRiskDimension(RiskDimension riskDimension) {
		return riskDimensionRepository.save(riskDimension);
	}
	
	@Override
	public RiskDimension getRiskDimensionById(String dimension) {
		List<RiskDimension> riskDimensions = riskDimensionRepository.findByDimension(dimension);
		return riskDimensions.get(0);
	}
	
	@Override
	public RiskDimension getRiskDimensionByDimension(RiskDimension riskDimension) {
		List<RiskDimension> riskDimensions = riskDimensionRepository.findByDimension(riskDimension.getDimension());
		return riskDimensions.get(0);
	}
	
	@Override
	public void deleteRiskDimension(RiskDimension riskDimension) {
		riskDimensionRepository.deleteById(riskDimension.getDimension());
	}
	
/*
	@Override
	public List<RiskDimension> getAllRiskDimension() {
		return riskDimensionRepository.findAll();
	}

	

	

	@Override
	public RiskDimension updateRiskDimension(RiskDimension riskDimension) {
		return riskDimensionRepository.save(riskDimension);
	}

	

	@Override
	public boolean checkDataIfPresent(RiskDimension riskDimension) {
		List<RiskDimension> riskDimensions = riskDimensionRepository.findByDimension(riskDimension.getDimension());
		return riskDimensions.isEmpty();
	}
	
	*/

}
