package com.nagarro.calculator.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.models.ScoreCap;

import com.nagarro.calculator.services.ScoreCapService;

@RestController
@CrossOrigin(origins="*")
public class ScoreCapController {

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "scoreCap";
	
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
	
	
	@GetMapping("/score-cap")
	public @ResponseBody List<ScoreCap> getScoreCap(){
		return scoreCapService.getAllScoreCap();
	}
	
	@PostMapping("/addScoreCap")
	public ScoreCap saveScoreCap(@RequestBody ScoreCap scoreCap) {
		return scoreCapService.saveScoreCap(scoreCap);
	}
	
	
	
	@GetMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCap> getSoreCap(@PathVariable String condition){
		ScoreCap scoreCap = scoreCapService.getScoreCapByCondition(condition);
		return ResponseEntity.ok(scoreCap);
	}
	
	@PutMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCap> updateScoreCap(@PathVariable String condition, @RequestBody ScoreCap scoreCapDetails){
		ScoreCap scoreCap = scoreCapService.getScoreCapById(scoreCapDetails);
		scoreCap.setCondition(scoreCapDetails.getCondition());
		scoreCap.setTotalRiskCappedScore(scoreCapDetails.getTotalRiskCappedScore());
		ScoreCap updatedScoreCap = scoreCapService.saveScoreCap(scoreCap);
		return ResponseEntity.ok(updatedScoreCap);
	}
	
	@DeleteMapping("/score-cap/{condition}")
	public ResponseEntity<Map<String, Boolean>> deleteScoreCap(@PathVariable String condition){
		ScoreCap scoreCap = scoreCapService.getScoreCapByCondition(condition);
		scoreCapService.deleteScoreCap(scoreCap);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	/*
	@Autowired
	private ScoreCapService scoreCapService;

	@GetMapping("/add-score-cap")
	public String addRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new ScoreCap());
		return "scoreCapAdd";
	}
	
	@PostMapping("/addScoreCap")
	public String saveScoreCap(@Valid @ModelAttribute("scoreCap") ScoreCap scoreCap, BindingResult bindingResult) {
		
		if(!scoreCapService.checkDataIfPresent(scoreCap)) {
			bindingResult.addError(new FieldError("scoreCap", "condition", "Condition already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Adding.");
			return "scoreCapAdd";
		}
		
		 scoreCapService.saveScoreCap(scoreCap);
		 return REDIRECT_PAGE;
	}
	
	@GetMapping("/update-score-cap")
	public String updateScoreCap(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new ScoreCap());
		return "scoreCapUpdate";
	}
	
	@PostMapping("/updateScoreCap")
	public String editScoreCap(@Valid @ModelAttribute("scoreCap") ScoreCap scoreCap, BindingResult bindingResult) {
		
		if(scoreCapService.checkDataIfPresent(scoreCap)) {
			bindingResult.addError(new FieldError("scoreCap", "condition", "Condition already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while updating.");
			return "scoreCapUpdate";
		}
		
		ScoreCap existingScoreCap = scoreCapService.getScoreCapById(scoreCap);
		existingScoreCap.setCondition(scoreCap.getCondition());
		existingScoreCap.setTotalRiskCappedScore(scoreCap.getTotalRiskCappedScore());
		
		scoreCapService.updateScoreCap(existingScoreCap);
		return REDIRECT_PAGE;
	}
	
	@GetMapping("/delete-score-cap")
	public String removeScoreCap(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new ScoreCap());
		return "scoreCapDelete";
	}
	
	@PostMapping("/deleteScoreCap")
	public String deleteScoreCap(@Valid @ModelAttribute("scoreCap") ScoreCap scoreCap, BindingResult bindingResult) {
		
		if(scoreCapService.checkDataIfPresent(scoreCap)) {
			bindingResult.addError(new FieldError("scoreCap", "condition", "Condition already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Deleting.");
			return "scoreCapDelete";
		}
		
		scoreCapService.deleteScoreCap(scoreCap);
		return REDIRECT_PAGE;
	}
	*/
}
