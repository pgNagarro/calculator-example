package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.RiskScoreLevel;

/**
 * Interface for Risk Score Level Service
 * @author parasgautam
 *
 */
public interface RiskScoreLevelService {
	
	List<RiskScoreLevel> getAllRiskScoreLevel();

	void addRiskScoreLevel(RiskScoreLevel riskScoreLevel);

	RiskScoreLevel saveRiskScoreLevel(RiskScoreLevel riskScoreLevel);

	RiskScoreLevel getRiskScoreLevelByScore(String score);
	
	RiskScoreLevel getRiskScoreLevelById(RiskScoreLevel riskScoreLevel);
	
	void deleteRiskScoreLevel(RiskScoreLevel riskScoreLevel);
//	
//	boolean checkDataIfPresent(RiskScoreLevel riskScoreLevel);
	
}
