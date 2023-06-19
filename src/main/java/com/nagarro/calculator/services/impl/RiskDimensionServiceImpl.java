package com.nagarro.calculator.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.repositories.RiskDimensionRepository;
import com.nagarro.calculator.services.RiskDimensionService;

@Service
public class RiskDimensionServiceImpl implements RiskDimensionService{

	@Autowired
	private RiskDimensionRepository riskDimensionRepository;

	@Override
	public List<RiskDimension> getAllRiskDimension() {
		return riskDimensionRepository.findAll();
	}

	@Override
	public RiskDimension saveRiskDimension(RiskDimension riskDimension) {
		return riskDimensionRepository.save(riskDimension);
	}

	@Override
	public RiskDimension getRiskDimensionById(RiskDimension riskDimension) {
		List<RiskDimension> riskDimensions = riskDimensionRepository.findByDimension(riskDimension.getDimension());
		return riskDimensions.get(0);
	}

	@Override
	public RiskDimension updateRiskDimension(RiskDimension riskDimension) {
		return riskDimensionRepository.save(riskDimension);
	}

	@Override
	public void deleteRiskDimension(RiskDimension riskDimension) {
		riskDimensionRepository.deleteById(riskDimension.getDimension());
	}

}
