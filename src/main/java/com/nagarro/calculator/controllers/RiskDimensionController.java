package com.nagarro.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.services.RiskDimensionService;

@Controller
public class RiskDimensionController {


	@Autowired
	private RiskDimensionService riskDimensionService;

	@GetMapping("/add-risk-dimension")
	public String addRiskScore(Model model) {
		RiskDimension riskDimension = new RiskDimension();
		model.addAttribute("riskDimension", riskDimension);
		return "riskDimensionAdd";
	}
	
	@PostMapping("/addRiskDimension")
	public String saveRiskDimension(@ModelAttribute("riskDimension") RiskDimension riskDimension) {
		 riskDimensionService.saveRiskDimension(riskDimension);
		 return "redirect:/risk-score";
	}
	
	@GetMapping("/update-risk-dimension")
	public String updateRiskDimension(Model model) {
		RiskDimension riskDimension = new RiskDimension();
		model.addAttribute("riskDimension", riskDimension);
		return "riskDimensionUpdate";
	}
	
	@PostMapping("/updateRiskDimension")
	public String editRiskDimension(@ModelAttribute("riskDimension") RiskDimension riskDimension) {
		
		RiskDimension existingRiskDimension = riskDimensionService.getRiskDimensionById(riskDimension);
		existingRiskDimension.setDimension(riskDimension.getDimension());
		existingRiskDimension.setWeight(riskDimension.getWeight());
		
		riskDimensionService.updateRiskDimension(existingRiskDimension);
		return "redirect:/risk-score";
	}
	
	@GetMapping("/delete-risk-dimension")
	public String removeRiskDimension(Model model) {
		RiskDimension riskDimension = new RiskDimension();
		model.addAttribute("riskDimension", riskDimension);
		return "riskDimensionDelete";
	}
	
	@PostMapping("/deleteRiskDimension")
	public String deleteRiskDimension(@ModelAttribute("riskDimension") RiskDimension riskDimension) {
		riskDimensionService.deleteRiskDimension(riskDimension);
		return "redirect:/risk-score";
	}
	
}
