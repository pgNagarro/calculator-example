package com.nagarro.calculator.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.calculator.models.RiskDimension;

/**
 * Interface for Risk Dimension Service
 * @author parasgautam
 *
 */
public interface RiskDimensionService {
	
	List<RiskDimension> getAllRiskDimension();
	
	RiskDimension saveRiskDimension(RiskDimension riskDimension) throws IOException;
	
	RiskDimension getRiskDimensionById(String dimension) throws IOException;

	void deleteRiskDimension(RiskDimension riskDimension);

}
