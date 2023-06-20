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

import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.services.RiskDimensionService;

@Controller
public class RiskDimensionController {


	private static final Logger logger = LoggerFactory.getLogger(RiskDimensionController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "riskDimension";

	
	@Autowired
	private RiskDimensionService riskDimensionService;

	@GetMapping("/add-risk-dimension")
	public String addRiskScore(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskDimension());
		return "riskDimensionAdd";
	}
	
	@PostMapping("/addRiskDimension")
	public String saveRiskDimension(@Valid @ModelAttribute("riskDimension") RiskDimension riskDimension, BindingResult bindingResult) {
		
		if(!riskDimensionService.checkDataIfPresent(riskDimension)) {
			bindingResult.addError(new FieldError("riskDimension", "dimension", "Dimension already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Adding.");
			return "riskDimensionAdd";
		}
		
		 riskDimensionService.saveRiskDimension(riskDimension);
		 return REDIRECT_PAGE;
	}
	
	@GetMapping("/update-risk-dimension")
	public String updateRiskDimension(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskDimension());
		return "riskDimensionUpdate";
	}
	
	@PostMapping("/updateRiskDimension")
	public String editRiskDimension(@Valid @ModelAttribute("riskDimension") RiskDimension riskDimension, BindingResult bindingResult) {
		
		if(riskDimensionService.checkDataIfPresent(riskDimension)) {
			bindingResult.addError(new FieldError("riskDimension", "dimension", "Dimension already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while updating.");
			return "riskDimensionUpdate";
		}
		
		RiskDimension existingRiskDimension = riskDimensionService.getRiskDimensionById(riskDimension);
		existingRiskDimension.setDimension(riskDimension.getDimension());
		existingRiskDimension.setWeight(riskDimension.getWeight());
		
		riskDimensionService.updateRiskDimension(existingRiskDimension);
		return REDIRECT_PAGE;
	}
	
	@GetMapping("/delete-risk-dimension")
	public String removeRiskDimension(Model model) {
		model.addAttribute(ATTRIBUTE_NAME, new RiskDimension());
		return "riskDimensionDelete";
	}
	
	@PostMapping("/deleteRiskDimension")
	public String deleteRiskDimension(@Valid @ModelAttribute("riskDimension") RiskDimension riskDimension, BindingResult bindingResult) {
		
		if(!riskDimensionService.checkDataIfPresent(riskDimension)) {
			bindingResult.addError(new FieldError("riskDimension", "dimension", "Dimension already present"));
		}
		
		if(bindingResult.hasErrors()) {
			logger.info("Validations errors while Deleting.");
			return "riskDimensionDelete";
		}
		
		riskDimensionService.deleteRiskDimension(riskDimension);
		return REDIRECT_PAGE;
	}
	
}
