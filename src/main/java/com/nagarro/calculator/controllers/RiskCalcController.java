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

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.services.RiskCalcService;


@Controller
public class RiskCalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskCalcController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "riskCalc";

	@Autowired
	private RiskCalcService riskCalcService;

	@GetMapping("/add-risk-calc-logic")
	public String addRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskCalc());
		return "riskCalcLogicAdd";
	}
	
	@PostMapping("/addRiskCalcLogic")
	public String saveRiskCalcLogic(@Valid @ModelAttribute("riskCalc") RiskCalc riskCalc, BindingResult bindingResult) {
		
		if(!riskCalcService.checkDataIfPresent(riskCalc)) {
			bindingResult.addError(new FieldError("riskCalc", "elementName", "Element name already present"));
		}
	
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Adding.");
			return "riskCalcLogicAdd";
		}
		
		 riskCalcService.saveRiskCalc(riskCalc);
		 return REDIRECT_PAGE;
	}
	
	@GetMapping("/update-risk-calc-logic")
	public String updateRiskCalc(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskCalc());
		return "riskCalcLogicUpdate";
	}
	
	@PostMapping("/updateRiskCalcLogic")
	public String editRiskCalc(@Valid @ModelAttribute("riskCalc") RiskCalc riskCalc, BindingResult bindingResult) {
		
		if(riskCalcService.checkDataIfPresent(riskCalc)) {
			bindingResult.addError(new FieldError("riskCalc", "elementName", "Element name already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Updating.");
			return "riskCalcLogicUpdate";
		}
		
		RiskCalc existingRiskCalc = riskCalcService.getRiskCalcLogicById(riskCalc);
		existingRiskCalc.setElementName(riskCalc.getElementName());
		existingRiskCalc.setFormula(riskCalc.getFormula());
		
		riskCalcService.updateRiskCalcLogic(existingRiskCalc);
		return REDIRECT_PAGE;
	}
	
	@GetMapping("/delete-risk-calc-logic")
	public String removeRiskCalcLogic(Model model) {
		model.addAttribute(ATTRIBUTE_NAME,  new RiskCalc());
		return "riskCalcLogicDelete";
	}
	
	@PostMapping("/deleteRiskCalcLogic")
	public String deleteRiskCalcLogic(@Valid @ModelAttribute("riskCalc") RiskCalc riskCalc, BindingResult bindingResult) {
		
		if(riskCalcService.checkDataIfPresent(riskCalc)) {
			bindingResult.addError(new FieldError("riskCalc", "elementName", "Element name already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Updating.");
			return "riskCalcLogicDelete";
		}
		
		riskCalcService.deleteRiskCalcLogic(riskCalc);
		return REDIRECT_PAGE;
	}

}
