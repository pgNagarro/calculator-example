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

import com.nagarro.calculator.models.ScoreCap;

import com.nagarro.calculator.services.ScoreCapService;

/**
 * Controller class for score cap
 * 
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins="*")
public class ScoreCapController {

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapController.class);
	
	@Autowired
	private ScoreCapService scoreCapService;
	
	/**
	 * Method to get all score cap data
	 * @return
	 */
	@GetMapping("/score-cap")
	public ResponseEntity<List<ScoreCap>> getScoreCap(){
		
		logger.info("Request received for fetching all score cap data");
		
		List<ScoreCap> scoreCapList = scoreCapService.getAllScoreCap();
		
		logger.info("Request completed for fetching all score cap data");
		
		return ResponseEntity.ok(scoreCapList);
		
	}
	
	/**
	 * Method for saving score cap data
	 * @param scoreCap
	 * @return
	 */
	@PostMapping("/addScoreCap")
	public ResponseEntity<ScoreCap> saveScoreCap(@RequestBody ScoreCap scoreCap) {
		
		logger.info("Request received for adding score cap data");
		
		ScoreCap newScoreCap = scoreCapService.saveScoreCap(scoreCap);
		
		logger.info("Request completed for adding score cap data");
		
		return ResponseEntity.ok(newScoreCap);
		
	}
	
	
	/**
	 * Method for getting single score cap data by condition
	 * @param condition
	 * @return
	 */
	@GetMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCap> getSoreCap(@PathVariable String condition){
		
		logger.info("Request received for getting single score cap data");
		
		ScoreCap scoreCap;
		
		try {
			
			scoreCap = scoreCapService.getScoreCapByCondition(condition);
			
			logger.info("Request completed for getting single score cap data");
			
			return ResponseEntity.ok(scoreCap);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}	
		
	}
	
	/**
	 * Method for updating score cap data
	 * @param condition
	 * @param scoreCapDetails
	 * @return
	 */
	@PutMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCap> updateScoreCap(@PathVariable String condition, @RequestBody ScoreCap scoreCapDetails){
		
		logger.info("Request received for updating score cap data");
		
		ScoreCap scoreCap;
		
		try {
			
			scoreCap = scoreCapService.getScoreCapByCondition(scoreCapDetails.getCondition());	
			scoreCap.setCondition(scoreCapDetails.getCondition());
			scoreCap.setTotalRiskCappedScore(scoreCapDetails.getTotalRiskCappedScore());
			
			ScoreCap updatedScoreCap = scoreCapService.saveScoreCap(scoreCap);
			
			logger.info("Request received for updating score cap data");
			
			return ResponseEntity.ok(updatedScoreCap);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
		}

	}
	
	/**
	 * Method for deleting score cap data
	 * @param condition
	 * @return
	 */
	@DeleteMapping("/score-cap/{condition}")
	public ResponseEntity<Map<String, Boolean>> deleteScoreCap(@PathVariable String condition){
		
		logger.info("Request received for deleting score cap data");
		
		ScoreCap scoreCap;
		
		try {
			
			scoreCap = scoreCapService.getScoreCapByCondition(condition);
			scoreCapService.deleteScoreCap(scoreCap);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request received for deleting score cap data");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			return ResponseEntity.ok(response);
			
		}
		
		
	}
	
}
