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

	@Override
	public RiskCalc saveRiskCalc(RiskCalc riskCalc) {
		return riskCalcRepository.save(riskCalc);
	}

	@Override
	public RiskCalc getRiskCalcLogicById(RiskCalc riskCalc) {
		List<RiskCalc> riskCalcs = riskCalcRepository.findByElementName(riskCalc.getElementName());
		return riskCalcs.get(0);
	}

	@Override
	public RiskCalc updateRiskCalcLogic(RiskCalc riskCalc) {
		return riskCalcRepository.save(riskCalc);
	}

	@Override
	public void deleteRiskCalcLogic(RiskCalc riskCalc) {
		riskCalcRepository.deleteById(riskCalc.getElementName());
	}

	@Override
	public boolean checkDataIfPresent(RiskCalc riskCalc) {
		List<RiskCalc> riskCalcs = riskCalcRepository.findByElementName(riskCalc.getElementName());
		return riskCalcs.isEmpty();
	}
}
