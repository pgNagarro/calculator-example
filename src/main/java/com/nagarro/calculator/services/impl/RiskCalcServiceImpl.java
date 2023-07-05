package com.nagarro.calculator.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.repositories.RiskCalcRepository;
import com.nagarro.calculator.services.RiskCalcService;

/**
 * Service Implementation Class for Risk Calculation Service
 * @author parasgautam
 *
 */
@Service
public class RiskCalcServiceImpl implements RiskCalcService{
	
	private static final Logger logger = LoggerFactory.getLogger(RiskCalcServiceImpl.class);

	@Autowired
	private RiskCalcRepository riskCalcRepository;
	
	@Override
	public void addRiskCalc(RiskCalc riskCalc) {
		logger.info("start : addRiskCalc");
		riskCalcRepository.save(riskCalc);
	}

	@Override
	public List<RiskCalc> getAllRiskCalcLogic() {
		logger.info("start : getAllRiskCalcLogic");
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
	public RiskCalc getRiskCalcLogicByName(String name) {
		List<RiskCalc> riskCalcs = riskCalcRepository.findByElementName(name);
		return riskCalcs.get(0);
	}
	
	@Override
	public void deleteRiskCalc(RiskCalc riskCalc) {
		riskCalcRepository.deleteById(riskCalc.getElementName());
	}
	
/*
	@Override
	public List<RiskCalc> getAllRiskCalcLogic() {
		return riskCalcRepository.findAll();
	}

	

	

	@Override
	public RiskCalc updateRiskCalcLogic(RiskCalc riskCalc) {
		return riskCalcRepository.save(riskCalc);
	}

	

	@Override
	public boolean checkDataIfPresent(RiskCalc riskCalc) {
		List<RiskCalc> riskCalcs = riskCalcRepository.findByElementName(riskCalc.getElementName());
		return riskCalcs.isEmpty();
	}
	*/

	
}
