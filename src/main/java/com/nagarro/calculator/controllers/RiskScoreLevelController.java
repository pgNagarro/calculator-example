package com.nagarro.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.services.RiskScoreLevelService;

@Controller
public class RiskScoreLevelController {
	
	@Autowired
	private RiskScoreLevelService riskScoreLevelService;

	@GetMapping("/add-risk-score-level")
	public String addRiskScore(Model model) {
		RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
		model.addAttribute("riskScoreLevel", riskScoreLevel);
		return "riskScoreLevelAdd";
	}
	
	@PostMapping("/addRiskScoreLevel")
	public String saveRiskScoreLevel(@ModelAttribute("riskScoreLevel") RiskScoreLevel riskScoreLevel) {
		 riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
		 return "redirect:/risk-score";
	}
	
	@GetMapping("/update-risk-score-level")
	public String updateRiskScoreLevel(Model model) {
		RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
		model.addAttribute("riskScoreLevel", riskScoreLevel);
		return "riskScoreLevelUpdate";
	}
	
	@PostMapping("/updateRiskScoreLevel")
	public String editRiskScoreLevel(@ModelAttribute("riskScoreLevel") RiskScoreLevel riskScoreLevel) {
		
		RiskScoreLevel existingRiskScoreLevel = riskScoreLevelService.getRiskScoreLevelById(riskScoreLevel);
		existingRiskScoreLevel.setScore(riskScoreLevel.getScore());
		existingRiskScoreLevel.setLevel(riskScoreLevel.getLevel());
		
		riskScoreLevelService.updateRiskScoreLevel(existingRiskScoreLevel);
		return "redirect:/risk-score";
	}
	
	@GetMapping("/delete-risk-score-level")
	public String removeRiskScoreLevel(Model model) {
		RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
		model.addAttribute("riskScoreLevel", riskScoreLevel);
		return "riskScoreLevelDelete";
	}
	
	@PostMapping("/deleteRiskScoreLevel")
	public String deleteRiskScoreLevel(@ModelAttribute("riskScoreLevel") RiskScoreLevel riskScoreLevel) {
		riskScoreLevelService.deleteRiskScoreLevel(riskScoreLevel);
		return "redirect:/risk-score";
	}

}
