package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskDimension;

/**
 * Interface for Risk Dimension Service
 * @author parasgautam
 *
 */
public interface RiskDimensionService {
	
	List<RiskDimension> getAllRiskDimension();

	void addRiskDimension(RiskDimension riskDimension);
	
	RiskDimension saveRiskDimension(RiskDimension riskDimension);
	
	RiskDimension getRiskDimensionById(String dimension);
	
	RiskDimension getRiskDimensionByDimension(RiskDimension riskDimension);

	void deleteRiskDimension(RiskDimension riskDimension);
//	
//	boolean checkDataIfPresent(RiskDimension riskDimension);
//	
}
