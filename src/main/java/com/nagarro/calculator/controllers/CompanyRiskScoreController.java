package com.nagarro.calculator.controllers;

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
import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.services.CompanyRiskScoreService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author parasgautam
 * 	 
 * Controller class for Company Risk Score to handle operations
 * on company risk score data
 */
@RestController
@CrossOrigin(origins="*")
public class CompanyRiskScoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyRiskScoreController.class);
	
	
	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;
	
	
	/**
	 * Method to get all data in company risk score table
	 * @return
	 */
	@GetMapping("/risk-score")
	public List<CompanyRiskScore> getCompanyRiskScore() {
		return companyRiskScoreService.getAllCompanyRiskScore();
	}
	
	/**
	 * Method for adding company risk score data
	 * @param companyRiskScore
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("/addRiskScore")	
	public CompanyRiskScore saveRiskScore(@RequestBody CompanyRiskScore companyRiskScore) throws IOException {
		
		if(!companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			logger.info("Company name already present");
		}
		
		System.out.println(companyRiskScore);
		 companyRiskScoreService.saveRiskScore(companyRiskScore);
		 return companyRiskScore;
	}
	
	
	/**
	 * Method for getting single company risk score data
	 * @param companyName
	 * @return
	 */
	@GetMapping("/risk-score/{companyName}")
	public ResponseEntity<CompanyRiskScore> getCompanyRiskScoreByCompanyName(@PathVariable String companyName){
		CompanyRiskScore companyRiskScore = companyRiskScoreService.getCompanyRiskScoreByCompanyName(companyName);
		return ResponseEntity.ok(companyRiskScore);
	}
	
	/**
	 * Method for updating company risk score data
	 * @param companyName
	 * @param riskScoreDetails
	 * @return
	 */
	@PutMapping("/risk-score/{companyName}")
	public ResponseEntity<CompanyRiskScore> updateCompanyRiskScore(@PathVariable String companyName, @RequestBody CompanyRiskScore riskScoreDetails){
		CompanyRiskScore riskScore = companyRiskScoreService.getCompanyRiskScoreByCompanyName(riskScoreDetails.getCompanyName());
		riskScore.setCompanyName(riskScoreDetails.getCompanyName());
		riskScore.setDimensions(riskScoreDetails.getDimensions());
	
		CompanyRiskScore updatedRiskScore = companyRiskScoreService.updateRiskScore(riskScore);
		return ResponseEntity.ok(updatedRiskScore);
	}

	/**
	 * Method for deleting company risk score data
	 * @param companyName
	 * @return
	 */
	@DeleteMapping("/risk-score/{companyName}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskScore(@PathVariable String companyName){
		CompanyRiskScore companyRiskScore = companyRiskScoreService.getCompanyRiskScoreByCompanyName(companyName);
		companyRiskScoreService.deleteCompanyRiskScore(companyRiskScore);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
