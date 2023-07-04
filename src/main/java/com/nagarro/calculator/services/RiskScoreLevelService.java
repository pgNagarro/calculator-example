package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskScoreLevel;

public interface RiskScoreLevelService {
	
	List<RiskScoreLevel> getAllRiskScoreLevel();

	void addRiskScoreLevel(RiskScoreLevel riskScoreLevel);

//	List<RiskScoreLevel> getAllRiskScoreLevel();
//	
	RiskScoreLevel saveRiskScoreLevel(RiskScoreLevel riskScoreLevel);
//	
	RiskScoreLevel getRiskScoreLevelByScore(String score);
	
	RiskScoreLevel getRiskScoreLevelById(RiskScoreLevel riskScoreLevel);
//	
//	RiskScoreLevel updateRiskScoreLevel(RiskScoreLevel riskScoreLevel);
//	
	void deleteRiskScoreLevel(RiskScoreLevel riskScoreLevel);
//	
//	boolean checkDataIfPresent(RiskScoreLevel riskScoreLevel);
	
}
