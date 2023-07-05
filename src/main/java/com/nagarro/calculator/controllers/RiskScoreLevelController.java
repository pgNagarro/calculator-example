package com.nagarro.calculator.controllers;

import java.util.Arrays;
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
	
	
	List<RiskScoreLevel> list = Arrays.asList(new RiskScoreLevel("81-100","Very low risk"),
												new RiskScoreLevel("61-80","Low risk"),
												new RiskScoreLevel("41-60","Intermediate"),
												new RiskScoreLevel("21-40","High risk"),
												new RiskScoreLevel("01-20","Very high risk"));
	
	@GetMapping("/add-risk-score-level")
	public @ResponseBody List<RiskScoreLevel> addRiskScoreLevel(){
		for(int i=0; i<list.size() ; i++) {
			riskScoreLevelService.addRiskScoreLevel(list.get(i));
		}
		return list;
	}
	
	/**
	 * Method to get all risk score level data
	 * @return
	 */
	@GetMapping("/risk-score-level")
	public @ResponseBody List<RiskScoreLevel> getRiskScoreLevel(){
		return riskScoreLevelService.getAllRiskScoreLevel();
	}
	
	/**
	 * Method to saving risk score level data
	 * @param riskScoreLevel
	 * @return
	 */
	@PostMapping("/addRiskScoreLevel")
	public RiskScoreLevel saveRiskScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {
		return riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
	}
	
	/**
	 * Method for getting single risk score level data by score
	 * @param score
	 * @return
	 */
	@GetMapping("/risk-score-level/{score}")
	public ResponseEntity<RiskScoreLevel> getRiskScoreLevel(@PathVariable String score){
		RiskScoreLevel riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(score);
		return ResponseEntity.ok(riskScoreLevel);
	}
	
	@PutMapping("/risk-score-level/{score}")
	public ResponseEntity<RiskScoreLevel> updateRiskScoreLevel(@PathVariable String score, @RequestBody RiskScoreLevel riskScoreLevelDetails){
		RiskScoreLevel riskScoreLevel = riskScoreLevelService.getRiskScoreLevelById(riskScoreLevelDetails);	
		riskScoreLevel.setScore(riskScoreLevelDetails.getScore());
		riskScoreLevel.setLevel(riskScoreLevelDetails.getLevel());
		RiskScoreLevel updatedRiskScoreLevel = riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
		return ResponseEntity.ok(updatedRiskScoreLevel);
	}
	
	@DeleteMapping("/risk-score-level/{score}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskScoreLevel(@PathVariable String score){
		RiskScoreLevel riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(score);
		riskScoreLevelService.deleteRiskScoreLevel(riskScoreLevel);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
