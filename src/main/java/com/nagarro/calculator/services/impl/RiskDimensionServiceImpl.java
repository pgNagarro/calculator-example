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

}
