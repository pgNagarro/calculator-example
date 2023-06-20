package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskScoreLevel;

public interface RiskScoreLevelService {

	List<RiskScoreLevel> getAllRiskScoreLevel();
	
	RiskScoreLevel saveRiskScoreLevel(RiskScoreLevel riskScoreLevel);
	
	RiskScoreLevel getRiskScoreLevelById(RiskScoreLevel riskScoreLevel);
	
	RiskScoreLevel updateRiskScoreLevel(RiskScoreLevel riskScoreLevel);
	
	void deleteRiskScoreLevel(RiskScoreLevel riskScoreLevel);
	
	boolean checkDataIfPresent(RiskScoreLevel riskScoreLevel);
	
}
