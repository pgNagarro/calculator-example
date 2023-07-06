package com.nagarro.calculator.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.services.RiskCalcService;

/**
 * Controller class for risk calculation logic
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins = "*") 
public class RiskCalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskCalcController.class);
	
	@Autowired
	private RiskCalcService riskCalcService;
	
	
	/**
	 * Method to get all risk calculation logic data
	 * @return
	 */
	@GetMapping("/risk-calc-logic")
	public ResponseEntity<List<RiskCalc>> getAllRiskCalcLogic(){
		
		logger.info("Request received for fetching all risk calculation logic data");
		
		List<RiskCalc> riskCalcList = riskCalcService.getAllRiskCalcLogic();
		
		logger.info("Request completed for fetching all risk calculation logic data");
		
		return ResponseEntity.ok(riskCalcList);
		
	}
	
	/**
	 * Method for saving risk calculation logic data
	 * @param riskCalc
	 * @return
	 */
	@PostMapping("/addRiskCalcLogic")
	public ResponseEntity<RiskCalc> saveRiskCalcLogic(@RequestBody RiskCalc riskCalc) {
		
		logger.info("Request received for adding risk calculation logic data");
		
		RiskCalc newRiskCalc = riskCalcService.saveRiskCalc(riskCalc);
		
		logger.info("Request completed for adding risk calculation logic data");
		
		return ResponseEntity.ok(newRiskCalc);
	}
	
	/**
	 * Method for getting single risk calculation logic data by element name
	 * @param elementName
	 * @return
	 */
	@GetMapping("/risk-calc-logic/{elementName}")
	public ResponseEntity<RiskCalc> getRiskCalcByElementName(@PathVariable String elementName){
		
		logger.info("Request received for getting single risk calculation logic data");
		
		RiskCalc riskCalc;
		
		try {
			
			riskCalc = riskCalcService.getRiskCalcLogicByName(elementName);
			
			logger.info("Request completed for getting single risk calculation logic data");
			
			return ResponseEntity.ok(riskCalc);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}
		
		
	}
	
	/**
	 * Method for updating risk calculation logic data
	 * @param elementName
	 * @param riskCalcDetails
	 * @return
	 */
	@PutMapping("/risk-calc-logic/{elementName}")
	public ResponseEntity<RiskCalc> updateRiskCalc(@PathVariable String elementName, @RequestBody RiskCalc riskCalcDetails){
		
		logger.info("Request received for updating risk calculation logic data");
		
		RiskCalc riskCalc;
		
		try {
			
			riskCalc = riskCalcService.getRiskCalcLogicByName(riskCalcDetails.getElementName());
			riskCalc.setElementName(riskCalcDetails.getElementName());
			riskCalc.setFormula(riskCalcDetails.getFormula());
			
			RiskCalc updatedRiskCalc = riskCalcService.saveRiskCalc(riskCalc);
			
			logger.info("Request received for updating risk calculation logic data");
			
			return ResponseEntity.ok(updatedRiskCalc);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}	
		
		
	}
	
	/**
	 * Method for deleting risk calculation logic data
	 * @param elementName
	 * @return
	 */
	@DeleteMapping("/risk-calc-logic/{elementName}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskCalc(@PathVariable String elementName){
		
		logger.info("Request received for deleting risk calculating logic data");
		
		RiskCalc riskCalc;
		
		try {
			
			riskCalc = riskCalcService.getRiskCalcLogicByName(elementName);
			riskCalcService.deleteRiskCalc(riskCalc);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request completed for deleting risk calculating logic data");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			
			return ResponseEntity.ok(null);
			
		}
		
	}

}
