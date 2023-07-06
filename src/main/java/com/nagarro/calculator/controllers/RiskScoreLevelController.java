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

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.services.RiskScoreLevelService;

/**
 * Controller class for risk score level
 * 
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins="*")
public class RiskScoreLevelController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskScoreLevelController.class);
	
	@Autowired
	private RiskScoreLevelService riskScoreLevelService;
	
	/**
	 * Method to get all risk score level data
	 * @return
	 */
	@GetMapping("/risk-score-level")
	public ResponseEntity<List<RiskScoreLevel>> getRiskScoreLevel(){
		
		logger.info("Request received for fetching all the risk score level data");
		
		List<RiskScoreLevel> riskScoreLevelList = riskScoreLevelService.getAllRiskScoreLevel();
		
		logger.info("Request completed for fetching all the risk score level data");
		
		return ResponseEntity.ok(riskScoreLevelList);
		
	}
	
	/**
	 * Method to saving risk score level data
	 * @param riskScoreLevel
	 * @return
	 */
	@PostMapping("/addRiskScoreLevel")
	public ResponseEntity<RiskScoreLevel> saveRiskScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {
		
		logger.info("Request received for adding risk score level data");
		
		RiskScoreLevel newRiskScoreLevel = riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
		
		logger.info("Request completed for adding risk score level data");
		
		return ResponseEntity.ok(newRiskScoreLevel);
		
	}
	
	/**
	 * Method for getting single risk score level data by score
	 * @param score
	 * @return
	 */
	@GetMapping("/risk-score-level/{score}")
	public ResponseEntity<RiskScoreLevel> getRiskScoreLevelByScore(@PathVariable String score){
		
		logger.info("Request received for getting single risk score level data");
		
		RiskScoreLevel riskScoreLevel;
		
		try {
			
			riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(score);
			
			logger.info("Request completed for getting single risk score level data");
			
			return ResponseEntity.ok(riskScoreLevel);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}
		
		
	}
	
	/**
	 * Method for updating risk score level data
	 * @param score
	 * @param riskScoreLevelDetails
	 * @return
	 */
	@PutMapping("/risk-score-level/{score}")
	public ResponseEntity<RiskScoreLevel> updateRiskScoreLevel(@PathVariable String score, @RequestBody RiskScoreLevel riskScoreLevelDetails){
		
		logger.info("Request received for updating risk score level");
		
		RiskScoreLevel riskScoreLevel;
		
		try {
			
			riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(riskScoreLevelDetails.getScore());
			
			riskScoreLevel.setScore(riskScoreLevelDetails.getScore());
			riskScoreLevel.setLevel(riskScoreLevelDetails.getLevel());
			
			RiskScoreLevel updatedRiskScoreLevel = riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
			
			logger.info("Request completed for updating risk score level");
			
			return ResponseEntity.ok(updatedRiskScoreLevel);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}	
		
		
	}
	
	/**
	 * Method for deleting risk score level data
	 * @param score
	 * @return
	 */
	@DeleteMapping("/risk-score-level/{score}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskScoreLevel(@PathVariable String score){
		
		logger.info("Request received for deleting risk score level");
		
		RiskScoreLevel riskScoreLevel;
		
		try {
			riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(score);
			
			riskScoreLevelService.deleteRiskScoreLevel(riskScoreLevel);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request completed for deleting risk score level");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			return ResponseEntity.ok(null);
			
		}
		
		
	}

}
