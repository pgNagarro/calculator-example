package com.nagarro.calculator.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.services.CompanyRiskScoreService;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins="*")
public class CompanyRiskScoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyRiskScoreController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "companyRiskScore";
	
	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;
	
	List<CompanyRiskScore> list = Arrays.asList(
			new CompanyRiskScore("TCS",
					Arrays.asList(new Dimensions("Information Security", 80), new Dimensions("Resilence", 60),
							new Dimensions("Conduct", 70))),
			new CompanyRiskScore("Infosys",
					Arrays.asList(new Dimensions("Information Security", 90), new Dimensions("Resilence", 50),
							new Dimensions("Conduct", 55))),
			new CompanyRiskScore("CreditSuisse", Arrays.asList(new Dimensions("Information Security", 50),
					new Dimensions("Resilence", 40), new Dimensions("Conduct", 30))));

	@GetMapping("/add-risk-score")
	public List<CompanyRiskScore> addCompanyRiskScore() {
		for (int i = 0; i < list.size(); i++) {
			companyRiskScoreService.addCompanyRiskScore(list.get(i));
		}
		return list;
	}
	
	@GetMapping("/risk-score")
	public List<CompanyRiskScore> getCompanyRiskScore() {
		return companyRiskScoreService.getAllCompanyRiskScore();
	}
	
	
	@PostMapping("/addRiskScore")	
	public CompanyRiskScore saveRiskScore(@RequestBody CompanyRiskScore companyRiskScore) {
		
		if(!companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			logger.error("Company name already present");
		}
		 return companyRiskScoreService.saveRiskScore(companyRiskScore);
	}
	
/*
	@GetMapping("/add-risk-score")
	public String addRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new CompanyRiskScore());
		return "riskScoreAdd";
	}
	
	@PostMapping("/addRiskScore")	
	public String saveRiskScore(@Valid @ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore, BindingResult bindingResult) {
		
		if(!companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			bindingResult.addError(new FieldError("companyRiskScore", "companyName", "Company name already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Adding.");
			return "riskScoreAdd";
		}
		 companyRiskScoreService.saveRiskScore(companyRiskScore);
		 return REDIRECT_PAGE;
	}
	
	@GetMapping("/update-risk-score")
	public String updateRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new CompanyRiskScore());
		return "riskScoreUpdate";
	}
	
	@PostMapping("/updateRiskScore")
	public String editRiskScore(@Valid @ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore, BindingResult bindingResult) {
		
		if(companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			bindingResult.addError(new FieldError("companyRiskScore", "companyName", "Company name already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while updating.");
			return "riskScoreUpdate";
		}
		
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
	public String deleteRiskScore(@Valid @ModelAttribute("companyRiskScore") CompanyRiskScore companyRiskScore, BindingResult bindingResult) {
		
		if(companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			bindingResult.addError(new FieldError("companyRiskScore", "companyName", "Company name already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while deleting.");
			return "riskScoreDelete";
		}
		
		companyRiskScoreService.deleteCompanyRiskScore(companyRiskScore);
		return REDIRECT_PAGE;
	}
	
	*/
}
