package com.nagarro.calculator.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.services.RiskCalcService;

/**
 * 
 * @author parasgautam
 *
 * Controller class for risk calculation logic
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
	public @ResponseBody List<RiskCalc> getAllRiskCalcLogic(){
		return riskCalcService.getAllRiskCalcLogic();
	}
	
	/**
	 * Method for saving risk calculation logic data
	 * @param riskCalc
	 * @return
	 */
	@PostMapping("/addRiskCalcLogic")
	public RiskCalc saveRiskCalcLogic(@RequestBody RiskCalc riskCalc) {
		return riskCalcService.saveRiskCalc(riskCalc);
	}
	
	/**
	 * Method for getting single risk calculation logic data by element name
	 * @param elementName
	 * @return
	 */
	@GetMapping("/risk-calc-logic/{elementName}")
	public ResponseEntity<RiskCalc> getRiskCalcByElementName(@PathVariable String elementName){
		RiskCalc riskCalc = riskCalcService.getRiskCalcLogicByName(elementName);
		return ResponseEntity.ok(riskCalc);
	}
	
	/**
	 * Method for updating risk calculation logic data
	 * @param elementName
	 * @param riskCalcDetails
	 * @return
	 */
	@PutMapping("/risk-calc-logic/{elementName}")
	public ResponseEntity<RiskCalc> updateRiskCalc(@PathVariable String elementName, @RequestBody RiskCalc riskCalcDetails){
		RiskCalc riskCalc = riskCalcService.getRiskCalcLogicById(riskCalcDetails);	
		riskCalc.setElementName(riskCalcDetails.getElementName());
		riskCalc.setFormula(riskCalcDetails.getFormula());
		RiskCalc updatedRiskCalc = riskCalcService.saveRiskCalc(riskCalc);
		return ResponseEntity.ok(updatedRiskCalc);
	}
	
	/**
	 * Method for deleting risk calculation logic data
	 * @param elementName
	 * @return
	 */
	@DeleteMapping("/risk-calc-logic/{elementName}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskCalc(@PathVariable String elementName){
		RiskCalc riskCalc = riskCalcService.getRiskCalcLogicByName(elementName);
		riskCalcService.deleteRiskCalc(riskCalc);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
