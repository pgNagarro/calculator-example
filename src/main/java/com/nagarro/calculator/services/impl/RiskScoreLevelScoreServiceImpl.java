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

}
