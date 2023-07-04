package com.nagarro.calculator.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.services.CompanyRiskScoreService;
import com.nagarro.calculator.services.RiskDimensionService;

@RestController
@CrossOrigin(origins="*")
public class RiskDimensionController {


	private static final Logger logger = LoggerFactory.getLogger(RiskDimensionController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "riskDimension";

	
	@Autowired
	private RiskDimensionService riskDimensionService;
	
	@Autowired
	private CompanyRiskScoreService companyRiskScoreService;
	
	/*
	 * @GetMapping("/add-risk-dimension") public @ResponseBody List<Dimensions>
	 * addRiskDimension(){ List<Dimensions> list =
	 * companyRiskScoreService.getAllCompanyRiskScore().get(0).getDimensions();
	 * for(int i=0 ; i<list.size() ; i++) { RiskDimension riskDimension = new
	 * RiskDimension(list.get(i).getDimensionName(),0);
	 * riskDimensionService.addRiskDimension(riskDimension); } return list; }
	 */
	
	@GetMapping("/risk-dimension")
	public @ResponseBody List<RiskDimension>getRiskDimension(){
		return riskDimensionService.getAllRiskDimension();
	}
	
	@PostMapping("/addRiskDimension")
	public RiskDimension saveRiskDimension(@RequestBody RiskDimension riskDimension) {
		return riskDimensionService.saveRiskDimension(riskDimension);
	}
	
	
	@GetMapping("/risk-dimension/{dimension}")
	public ResponseEntity<RiskDimension> getRiskDimension(@PathVariable String dimension){
		RiskDimension riskDimension = riskDimensionService.getRiskDimensionById(dimension);
		return ResponseEntity.ok(riskDimension);
	}
	
	@PutMapping("/risk-dimension/{dimension}")
	public ResponseEntity<RiskDimension> updateRiskDimension(@PathVariable String dimension, @RequestBody RiskDimension riskDimensionDetails){
		RiskDimension riskDimension = riskDimensionService.getRiskDimensionByDimension(riskDimensionDetails);	
		riskDimension.setDimension(riskDimensionDetails.getDimension());
		riskDimension.setWeight(riskDimensionDetails.getWeight());
		RiskDimension updatedRiskDimension = riskDimensionService.saveRiskDimension(riskDimension);
		return ResponseEntity.ok(updatedRiskDimension);
	}
	
	@DeleteMapping("/risk-dimension/{dimension}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskDimension(@PathVariable String dimension){
		RiskDimension riskDimension = riskDimensionService.getRiskDimensionById(dimension);
		riskDimensionService.deleteRiskDimension(riskDimension);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
	/*

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
	*/
	
}
