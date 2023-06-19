package com.nagarro.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.nagarro.calculator.models.ScoreCap;

import com.nagarro.calculator.services.ScoreCapService;

@Controller
public class ScoreCapController {

	@Autowired
	private ScoreCapService scoreCapService;

	@GetMapping("/add-score-cap")
	public String addRiskScore(Model model) {
		ScoreCap scoreCap = new ScoreCap();
		model.addAttribute("scoreCap", scoreCap);
		return "scoreCapAdd";
	}
	
	@PostMapping("/addScoreCap")
	public String saveScoreCap(@ModelAttribute("scoreCap") ScoreCap scoreCap) {
		 scoreCapService.saveScoreCap(scoreCap);
		 return "redirect:/risk-score";
	}
	
	@GetMapping("/update-score-cap")
	public String updateScoreCap(Model model) {
		ScoreCap scoreCap = new ScoreCap();
		model.addAttribute("scoreCap", scoreCap);
		return "scoreCapUpdate";
	}
	
	@PostMapping("/updateScoreCap")
	public String editScoreCap(@ModelAttribute("scoreCap") ScoreCap scoreCap) {
		
		ScoreCap existingScoreCap = scoreCapService.getScoreCapById(scoreCap);
		existingScoreCap.setCondition(scoreCap.getCondition());
		existingScoreCap.setTotalRiskCappedScore(scoreCap.getTotalRiskCappedScore());
		
		scoreCapService.updateScoreCap(existingScoreCap);
		return "redirect:/risk-score";
	}
	
	@GetMapping("/delete-score-cap")
	public String removeScoreCap(Model model) {
		ScoreCap scoreCap = new ScoreCap();
		model.addAttribute("scoreCap", scoreCap);
		return "scoreCapDelete";
	}
	
	@PostMapping("/deleteScoreCap")
	public String deleteScoreCap(@ModelAttribute("scoreCap") ScoreCap scoreCap) {
		scoreCapService.deleteScoreCap(scoreCap);
		return "redirect:/risk-score";
	}
}
