package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskCalc;


public interface RiskCalcService {

	List<RiskCalc> getAllRiskCalcLogic();
	
	RiskCalc saveRiskCalc(RiskCalc riskCalc);
	
	RiskCalc getRiskCalcLogicById(RiskCalc riskCalc);
	
	RiskCalc updateRiskCalcLogic(RiskCalc riskCalc);
	
	void deleteRiskCalcLogic(RiskCalc riskCalc);
	
}
