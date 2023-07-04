package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskDimension;

public interface RiskDimensionService {
	
	List<RiskDimension> getAllRiskDimension();

	void addRiskDimension(RiskDimension riskDimension);

//	List<RiskDimension> getAllRiskDimension();
//	
	RiskDimension saveRiskDimension(RiskDimension riskDimension);
//	
	RiskDimension getRiskDimensionById(String dimension);
	
	RiskDimension getRiskDimensionByDimension(RiskDimension riskDimension);
//	
//	RiskDimension updateRiskDimension(RiskDimension riskDimension);
//	
	void deleteRiskDimension(RiskDimension riskDimension);
//	
//	boolean checkDataIfPresent(RiskDimension riskDimension);
//	
}
