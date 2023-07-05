package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskCalc;

/**
 * Interface for Risk Calculation Logic Service
 * @author parasgautam
 *
 */
public interface RiskCalcService {
	
	
	
		
	List<RiskCalc> getAllRiskCalcLogic();

	void addRiskCalc(RiskCalc riskCalc);
	RiskCalc getRiskCalcLogicByName(String name);
	
	RiskCalc saveRiskCalc(RiskCalc riskCalc);
	
	RiskCalc getRiskCalcLogicById(RiskCalc riskCalc);

	void deleteRiskCalc(RiskCalc riskCalc);
//	
//	boolean checkDataIfPresent(RiskCalc riskCalc);
	
}
