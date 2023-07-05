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
	
	
	List<ScoreCap> list = Arrays.asList(new ScoreCap("One \"very high risk\"",40),
										new ScoreCap("Two \"very high risk\"",30),
										new ScoreCap("One \"high risk\"",60),
										new ScoreCap("Two \"high risk\"",50));
	
	@GetMapping("/add-score-cap")
	public @ResponseBody List<ScoreCap> addScoreCap(){
		for(int i=0; i<list.size() ; i++) {
			scoreCapService.addScoreCap(list.get(i));
		}
		return list;
	}
	
	/**
	 * Method to get all score cap data
	 * @return
	 */
	@GetMapping("/score-cap")
	public @ResponseBody List<ScoreCap> getScoreCap(){
		return scoreCapService.getAllScoreCap();
	}
	
	/**
	 * Method for saving score cap data
	 * @param scoreCap
	 * @return
	 */
	@PostMapping("/addScoreCap")
	public ScoreCap saveScoreCap(@RequestBody ScoreCap scoreCap) {
		return scoreCapService.saveScoreCap(scoreCap);
	}
	
	
	/**
	 * Method for getting single score cap data by condition
	 * @param condition
	 * @return
	 */
	@GetMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCap> getSoreCap(@PathVariable String condition){
		ScoreCap scoreCap = scoreCapService.getScoreCapByCondition(condition);
		return ResponseEntity.ok(scoreCap);
	}
	
	/**
	 * Method for updating score cap data
	 * @param condition
	 * @param scoreCapDetails
	 * @return
	 */
	@PutMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCap> updateScoreCap(@PathVariable String condition, @RequestBody ScoreCap scoreCapDetails){
		ScoreCap scoreCap = scoreCapService.getScoreCapById(scoreCapDetails);
		scoreCap.setCondition(scoreCapDetails.getCondition());
		scoreCap.setTotalRiskCappedScore(scoreCapDetails.getTotalRiskCappedScore());
		ScoreCap updatedScoreCap = scoreCapService.saveScoreCap(scoreCap);
		return ResponseEntity.ok(updatedScoreCap);
	}
	
	/**
	 * Method for deleting score cap data
	 * @param condition
	 * @return
	 */
	@DeleteMapping("/score-cap/{condition}")
	public ResponseEntity<Map<String, Boolean>> deleteScoreCap(@PathVariable String condition){
		ScoreCap scoreCap = scoreCapService.getScoreCapByCondition(condition);
		scoreCapService.deleteScoreCap(scoreCap);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
