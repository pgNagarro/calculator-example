package com.nagarro.calculator.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.nagarro.calculator.models.ScoreCap;

import com.nagarro.calculator.services.ScoreCapService;

@Controller
public class ScoreCapController {

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "scoreCap";
	
	
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
}
