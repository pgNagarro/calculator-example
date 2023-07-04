package com.nagarro.calculator.controllers;

import java.util.Arrays;
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

import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.services.RiskScoreLevelService;

@RestController
@CrossOrigin(origins="*")
public class RiskScoreLevelController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskScoreLevelController.class);
	
	private static final String REDIRECT_PAGE = "redirect:/risk-score";
	
	private static final String ATTRIBUTE_NAME = "riskScoreLevel";
	
	@Autowired
	private RiskScoreLevelService riskScoreLevelService;
	
	
	List<RiskScoreLevel> list = Arrays.asList(new RiskScoreLevel("81-100","Very low risk"),
												new RiskScoreLevel("61-80","Low risk"),
												new RiskScoreLevel("41-60","Intermediate"),
												new RiskScoreLevel("21-40","High risk"),
												new RiskScoreLevel("01-20","Very high risk"));
	
	@GetMapping("/add-risk-score-level")
	public @ResponseBody List<RiskScoreLevel> addRiskScoreLevel(){
		for(int i=0; i<list.size() ; i++) {
			riskScoreLevelService.addRiskScoreLevel(list.get(i));
		}
		return list;
	}
	
	@GetMapping("/risk-score-level")
	public @ResponseBody List<RiskScoreLevel> getRiskScoreLevel(){
		return riskScoreLevelService.getAllRiskScoreLevel();
	}
	
	@PostMapping("/addRiskScoreLevel")
	public RiskScoreLevel saveRiskScoreLevel(@RequestBody RiskScoreLevel riskScoreLevel) {
		return riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
	}
	
	
	@GetMapping("/risk-score-level/{score}")
	public ResponseEntity<RiskScoreLevel> getRiskScoreLevel(@PathVariable String score){
		RiskScoreLevel riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(score);
		return ResponseEntity.ok(riskScoreLevel);
	}
	
	@PutMapping("/risk-score-level/{score}")
	public ResponseEntity<RiskScoreLevel> updateRiskScoreLevel(@PathVariable String score, @RequestBody RiskScoreLevel riskScoreLevelDetails){
		RiskScoreLevel riskScoreLevel = riskScoreLevelService.getRiskScoreLevelById(riskScoreLevelDetails);	
		riskScoreLevel.setScore(riskScoreLevelDetails.getScore());
		riskScoreLevel.setLevel(riskScoreLevelDetails.getLevel());
		RiskScoreLevel updatedRiskScoreLevel = riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel);
		return ResponseEntity.ok(updatedRiskScoreLevel);
	}
	
	@DeleteMapping("/risk-score-level/{score}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskScoreLevel(@PathVariable String score){
		RiskScoreLevel riskScoreLevel = riskScoreLevelService.getRiskScoreLevelByScore(score);
		riskScoreLevelService.deleteRiskScoreLevel(riskScoreLevel);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	/*
	
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
*/
}
