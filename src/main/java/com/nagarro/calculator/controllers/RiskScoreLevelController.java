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

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.services.RiskScoreLevelService;

@Controller
public class RiskScoreLevelController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskScoreLevelController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "riskScoreLevel";
	
	@Autowired
	private RiskScoreLevelService riskScoreLevelService;

	@GetMapping("/add-risk-score-level")
	public String addRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskScoreLevel());
		return "riskScoreLevelAdd";
	}
	
	@PostMapping("/addRiskScoreLevel")
	public String saveRiskScoreLevel(@Valid @ModelAttribute("riskScoreLevel") RiskScoreLevel riskScoreLevel, BindingResult bindingResult) {
		
		if(!riskScoreLevelService.checkDataIfPresent(riskScoreLevel)) {
			bindingResult.addError(new FieldError("riskScoreLevel", "score", "Score already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Adding.");
			return "riskScoreLevelAdd";
		}
		
		 riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
		 return REDIRECT_PAGE;
	}
	
	@GetMapping("/update-risk-score-level")
	public String updateRiskScoreLevel(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskScoreLevel());
		return "riskScoreLevelUpdate";
	}
	
	@PostMapping("/updateRiskScoreLevel")
	public String editRiskScoreLevel(@Valid @ModelAttribute("riskScoreLevel") RiskScoreLevel riskScoreLevel, BindingResult bindingResult) {
		
		if(!riskScoreLevelService.checkDataIfPresent(riskScoreLevel)) {
			bindingResult.addError(new FieldError("riskScoreLevel", "score", "Score already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while updating.");
			return "riskScoreLevelUpdate";
		}
		
		RiskScoreLevel existingRiskScoreLevel = riskScoreLevelService.getRiskScoreLevelById(riskScoreLevel);
		existingRiskScoreLevel.setScore(riskScoreLevel.getScore());
		existingRiskScoreLevel.setLevel(riskScoreLevel.getLevel());
		
		riskScoreLevelService.updateRiskScoreLevel(existingRiskScoreLevel);
		return REDIRECT_PAGE;
	}
	
	@GetMapping("/delete-risk-score-level")
	public String removeRiskScoreLevel(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskScoreLevel());
		return "riskScoreLevelDelete";
	}
	
	@PostMapping("/deleteRiskScoreLevel")
	public String deleteRiskScoreLevel(@Valid @ModelAttribute("riskScoreLevel") RiskScoreLevel riskScoreLevel, BindingResult bindingResult) {
		
		if(!riskScoreLevelService.checkDataIfPresent(riskScoreLevel)) {
			bindingResult.addError(new FieldError("riskScoreLevel", "score", "Score already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while deleting.");
			return "riskScoreLevelDelete";
		}
		
		riskScoreLevelService.deleteRiskScoreLevel(riskScoreLevel);
		return REDIRECT_PAGE;
	}

}
