package com.nagarro.calculator.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.calculator.models.RiskScoreLevel;

/**
 * Interface for Risk Score Level Service
 * @author parasgautam
 *
 */
public interface RiskScoreLevelService {
	
	List<RiskScoreLevel> getAllRiskScoreLevel();

	RiskScoreLevel saveRiskScoreLevel(RiskScoreLevel riskScoreLevel);

	RiskScoreLevel getRiskScoreLevelByScore(String score) throws IOException;
	
	void deleteRiskScoreLevel(RiskScoreLevel riskScoreLevel);

}
