package com.nagarro.calculator.services.impl;

import java.io.IOException;
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
	

	/**
	 * Method to get all risk calculation logic data
	 */
	@Override
	public List<RiskCalc> getAllRiskCalcLogic() {
		
		logger.info("start : getAllRiskCalcLogic");
		return riskCalcRepository.findAll();
		
	}

	/**
	 * Method to save risk calculation logic data
	 */
	@Override
	public RiskCalc saveRiskCalc(RiskCalc riskCalc) {
		
		logger.info("start : saveRiskCalcLogic");
		return riskCalcRepository.save(riskCalc);
		
	}
	

	
	/**
	 * Method to get risk calculation logic by name
	 * @throws IOException 
	 */
	@Override
	public RiskCalc getRiskCalcLogicByName(String name) throws IOException {
		
		logger.info("start : getRiskCalcLogicByName");
		List<RiskCalc> riskCalcs = riskCalcRepository.findByElementName(name);
		
		if(riskCalcs.isEmpty()) {
			throw new IOException("Risk calculation logic data not found");
		}
		return riskCalcs.get(0);
		
	}
	
	/**
	 * Method to delete risk calculation logic data from repo
	 */
	@Override
	public void deleteRiskCalc(RiskCalc riskCalc) {
		
		logger.info("start : deleteRiskCalcLogic");
		riskCalcRepository.deleteById(riskCalc.getElementName());
		
	}
		
}
