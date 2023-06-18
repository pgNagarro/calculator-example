package com.nagarro.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nagarro.calculator.services.CompanyRiskScoreService;
import com.nagarro.calculator.services.RiskCalcService;
import com.nagarro.calculator.services.RiskDimensionService;
import com.nagarro.calculator.services.RiskScoreLevelService;
import com.nagarro.calculator.services.ScoreCapService;

@Controller
public class ViewController {

	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;
	
	@Autowired
	private RiskCalcService riskCalcService;
	
	@Autowired
	private RiskDimensionService riskDimensionService;
	
	@Autowired
	private RiskScoreLevelService riskScoreLevelService;
	
	@Autowired
	private ScoreCapService scoreCapService;

	@GetMapping("/risk-score")
	public String listRiskScore(Model model) {
		model.addAttribute("riskScores", companyRiskScoreService.getAllRiskScore());
		model.addAttribute("riskDimensions", riskDimensionService.getAllRiskDimension());
		model.addAttribute("riskCalcLogic", riskCalcService.getAllRiskCalcLogic());
		model.addAttribute("riskScoreLevel", riskScoreLevelService.getAllRiskScoreLevel());
		model.addAttribute("scoreCaps", scoreCapService.getAllScoreCap());
		return "view";
	}

}
