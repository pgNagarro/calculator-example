package com.nagarro.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.services.RiskCalcService;


@Controller
public class RiskCalcController {

	@Autowired
	private RiskCalcService riskCalcService;

	@GetMapping("/add-risk-calc-logic")
	public String addRiskScore(Model model) {
		RiskCalc riskCalc = new RiskCalc();
		model.addAttribute("riskCalc", riskCalc);
		return "riskCalcLogicAdd";
	}
	
	@PostMapping("/addRiskCalcLogic")
	public String saveRiskCalcLogic(@ModelAttribute("riskCalc") RiskCalc riskCalc) {
		 riskCalcService.saveRiskCalc(riskCalc);
		 return "redirect:/risk-score";
	}
	
	@GetMapping("/update-risk-calc-logic")
	public String updateRiskCalc(Model model) {
		RiskCalc riskCalc = new RiskCalc();
		model.addAttribute("riskCalc",  riskCalc);
		return "riskCalcLogicUpdate";
	}
	
	@PostMapping("/updateRiskCalcLogic")
	public String editRiskCalc(@ModelAttribute("riskCalc") RiskCalc riskCalc) {
		
		RiskCalc existingRiskCalc = riskCalcService.getRiskCalcLogicById(riskCalc);
		existingRiskCalc.setElementName(riskCalc.getElementName());
		existingRiskCalc.setFormula(riskCalc.getFormula());
		
		riskCalcService.updateRiskCalcLogic(existingRiskCalc);
		return "redirect:/risk-score";
	}
	
	@GetMapping("/delete-risk-calc-logic")
	public String removeRiskCalcLogic(Model model) {
		RiskCalc riskCalc = new RiskCalc();
		model.addAttribute("riskCalc",  riskCalc);
		return "riskCalcLogicDelete";
	}
	
	@PostMapping("/deleteRiskCalcLogic")
	public String deleteRiskCalcLogic(@ModelAttribute("riskCalc") RiskCalc riskCalc) {
		riskCalcService.deleteRiskCalcLogic(riskCalc);
		return "redirect:/risk-score";
	}

}
