package com.nagarro.calculator.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.repositories.RiskScoreLevelRepository;
import com.nagarro.calculator.services.RiskScoreLevelService;

@Service
public class RiskScoreLevelScoreServiceImpl implements RiskScoreLevelService{

	@Autowired
	private RiskScoreLevelRepository riskScoreLevelRepository;

	@Override
	public List<RiskScoreLevel> getAllRiskScoreLevel() {
		return riskScoreLevelRepository.findAll();
	}

	@Override
	public RiskScoreLevel saveRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
		return riskScoreLevelRepository.save(riskScoreLevel);
	}

	@Override
	public RiskScoreLevel getRiskScoreLevelById(RiskScoreLevel riskScoreLevel) {
		List<RiskScoreLevel> riskScoreLevels = riskScoreLevelRepository.findByScore(riskScoreLevel.getScore());
		return riskScoreLevels.get(0);
	}

	@Override
	public RiskScoreLevel updateRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
		return riskScoreLevelRepository.save(riskScoreLevel);
	}

	@Override
	public void deleteRiskScoreLevel(RiskScoreLevel riskScoreLevel) {
		riskScoreLevelRepository.deleteById(riskScoreLevel.getScore());		
	}

	@Override
	public boolean checkDataIfPresent(RiskScoreLevel riskScoreLevel) {
		List<RiskScoreLevel> riskScoreLevels = riskScoreLevelRepository.findByScore(riskScoreLevel.getScore());
		return riskScoreLevels.isEmpty();
	}

}
