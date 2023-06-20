package com.nagarro.calculator.controllers;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
	
	//private static final Logger logger = LoggerFactory.getLogger(CompanyRiskScoreController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "companyRiskScore";
	
	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;

	@GetMapping("/add-risk-score")
	public String addRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new CompanyRiskScore());
		return "riskScoreAdd";
	}
	
	@PostMapping("/addRiskScore")
	public String saveRiskScore(@ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore) {
		 companyRiskScoreService.saveRiskScore(companyRiskScore);
		 return REDIRECT_PAGE;
	}
	
	@GetMapping("/update-risk-score")
	public String updateRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new CompanyRiskScore());
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
		return REDIRECT_PAGE;
	}
	
	@GetMapping("/delete-risk-score")
	public String removeRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new CompanyRiskScore());
		return "riskScoreDelete";
	}
	
	@PostMapping("/deleteRiskScore")
	public String deleteRiskScore(@ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore) {
		companyRiskScoreService.deleteCompanyRiskScore(companyRiskScore);
		return REDIRECT_PAGE;
	}
	
}
