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
import com.nagarro.calculator.services.CompanyRiskScoreService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Controller class for Company Risk Score to handle operations
 * on company risk score data
 * 
 * @author parasgautam
 * 
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
	public ResponseEntity<List<CompanyRiskScore>> getCompanyRiskScore() {
		
		logger.info("Request received for fetching company risk scores");
		
		List<CompanyRiskScore> companyRiskScoreList = companyRiskScoreService.getAllCompanyRiskScore();
		
		logger.info("Request completed for fetching company risk scores");
		
		return ResponseEntity.ok(companyRiskScoreList);
		
	}
	
	/**
	 * Method for adding company risk score data
	 * @param companyRiskScore
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("/addRiskScore")	
	public ResponseEntity<CompanyRiskScore> saveRiskScore(@RequestBody CompanyRiskScore companyRiskScore) throws IOException {
		
		logger.info("Request received for adding company risk score");
		
		if(!companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			logger.info("Company name already present");
		}
		
		 companyRiskScoreService.saveRiskScore(companyRiskScore);
		 
		 logger.info("Request completed for adding company risk score");
		 
		 return ResponseEntity.ok(companyRiskScore);
	}
	
	
	/**
	 * Method for getting single company risk score data
	 * @param companyName
	 * @return
	 */
	@GetMapping("/risk-score/{companyName}")
	public ResponseEntity<CompanyRiskScore> getCompanyRiskScoreByCompanyName(@PathVariable String companyName){
		
		logger.info("Request received for getting single company risk score");
		
		CompanyRiskScore companyRiskScore;
		
		try {
			
			companyRiskScore = companyRiskScoreService.getCompanyRiskScoreByCompanyName(companyName);
		
		
		logger.info("Request completed for getting single company risk score");
		
		return ResponseEntity.ok(companyRiskScore);
		
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}
		
	}
	
	/**
	 * Method for updating company risk score data
	 * @param companyName
	 * @param riskScoreDetails
	 * @return
	 */
	@PutMapping("/risk-score/{companyName}")
	public ResponseEntity<CompanyRiskScore> updateCompanyRiskScore(@PathVariable String companyName, @RequestBody CompanyRiskScore riskScoreDetails){
		
		logger.info("Request received for updating company risk score data");
		
		CompanyRiskScore riskScore;
		
		try {
			
			riskScore = companyRiskScoreService.getCompanyRiskScoreByCompanyName(riskScoreDetails.getCompanyName());
		
		riskScore.setCompanyName(riskScoreDetails.getCompanyName());
		riskScore.setDimensions(riskScoreDetails.getDimensions());
	
		CompanyRiskScore updatedRiskScore = companyRiskScoreService.updateRiskScore(riskScore);
		
		logger.info("Request completed for updating company risk score data");
		
		return ResponseEntity.ok(updatedRiskScore);
		
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.ok(null);
		}
	}

	/**
	 * Method for deleting company risk score data
	 * @param companyName
	 * @return
	 */
	@DeleteMapping("/risk-score/{companyName}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskScore(@PathVariable String companyName){
		
		logger.info("Request received for deleting company risk score");
		
		CompanyRiskScore companyRiskScore;
		try {
			companyRiskScore = companyRiskScoreService.getCompanyRiskScoreByCompanyName(companyName);
		
			companyRiskScoreService.deleteCompanyRiskScore(companyRiskScore);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request completed for deleting company risk score");
			
			return ResponseEntity.ok(response);
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			return ResponseEntity.ok(response);
			
		}
		
	}
	
}
