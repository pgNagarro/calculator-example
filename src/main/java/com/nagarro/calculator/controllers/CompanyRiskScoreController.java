package com.nagarro.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.services.CompanyRiskScoreService;

@Controller
public class CompanyRiskScoreController {
	
	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;

	@GetMapping("/add-risk-score")
	public String addRiskScore(Model model) {
		CompanyRiskScore companyRiskScore = new CompanyRiskScore();
		model.addAttribute("companyRiskScore", companyRiskScore);
		return "riskScoreAdd";
	}
	
	@PostMapping("/addRiskScore")
	public String saveRiskScore(@ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore) {
		 companyRiskScoreService.saveRiskScore(companyRiskScore);
		 return "redirect:/risk-score";
	}
	
	@GetMapping("/update-risk-score")
	public String updateRiskScore(Model model) {
		CompanyRiskScore companyRiskScore = new CompanyRiskScore();
		model.addAttribute("companyRiskScore", companyRiskScore);
		return "riskScoreUpdate";
	}
	
	@PostMapping("/updateRiskScore")
	public String editRiskScore(@ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore) {
		
		CompanyRiskScore existingCompanyRiskScore = companyRiskScoreService.getRiskScoreByName(companyRiskScore);
		existingCompanyRiskScore.setCompanyName(companyRiskScore.getCompanyName());
		existingCompanyRiskScore.setInformationSecurity(companyRiskScore.getInformationSecurity());
		existingCompanyRiskScore.setResilience(companyRiskScore.getResilience());
		existingCompanyRiskScore.setConduct(companyRiskScore.getConduct());
		
		companyRiskScoreService.updateCompanyRiskScore(existingCompanyRiskScore);
		return "redirect:/risk-score";
	}
	
	@GetMapping("/delete-risk-score")
	public String removeRiskScore(Model model) {
		CompanyRiskScore companyRiskScore = new CompanyRiskScore();
		model.addAttribute("companyRiskScore", companyRiskScore);
		return "riskScoreDelete";
	}
	
	@PostMapping("/deleteRiskScore")
	public String deleteRiskScore(@ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore) {
		companyRiskScoreService.deleteCompanyRiskScore(companyRiskScore);
		return "redirect:/risk-score";
	}
	
	
}
