package com.nagarro.calculator.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.calculator.models.RiskCalc;

/**
 * Interface for Risk Calculation Logic Service
 * @author parasgautam
 *
 */
public interface RiskCalcService {
		
	List<RiskCalc> getAllRiskCalcLogic();
	
	RiskCalc getRiskCalcLogicByName(String name) throws IOException;
	
	RiskCalc saveRiskCalc(RiskCalc riskCalc);	

	void deleteRiskCalc(RiskCalc riskCalc);
	
}
