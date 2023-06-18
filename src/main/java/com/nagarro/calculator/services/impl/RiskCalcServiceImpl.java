package com.nagarro.calculator.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.repositories.RiskCalcRepository;
import com.nagarro.calculator.services.RiskCalcService;

@Service
public class RiskCalcServiceImpl implements RiskCalcService{

	@Autowired
	private RiskCalcRepository riskCalcRepository;

	@Override
	public List<RiskCalc> getAllRiskCalcLogic() {
		return riskCalcRepository.findAll();
	}
}
