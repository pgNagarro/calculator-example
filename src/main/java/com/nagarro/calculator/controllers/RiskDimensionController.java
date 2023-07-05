package com.nagarro.calculator.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.services.CompanyRiskScoreService;
import com.nagarro.calculator.services.RiskDimensionService;

/**
 * Controller class for risk dimension
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins="*")
public class RiskDimensionController {


	private static final Logger logger = LoggerFactory.getLogger(RiskDimensionController.class);
	
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
	
	/**
	 * Method to get all risk dimension data
	 * @return
	 */
	@GetMapping("/risk-dimension")
	public @ResponseBody List<RiskDimension>getRiskDimension(){
		return riskDimensionService.getAllRiskDimension();
	}
	
	/**
	 * Method for saving risk dimension data
	 * @param riskDimension
	 * @return
	 */
	@PostMapping("/addRiskDimension")
	public RiskDimension saveRiskDimension(@RequestBody RiskDimension riskDimension) {
		return riskDimensionService.saveRiskDimension(riskDimension);
	}
	
	/**
	 * Method for getting single risk dimension data by dimension
	 * @param dimension
	 * @return
	 */
	@GetMapping("/risk-dimension/{dimension}")
	public ResponseEntity<RiskDimension> getRiskDimension(@PathVariable String dimension){
		RiskDimension riskDimension = riskDimensionService.getRiskDimensionById(dimension);
		return ResponseEntity.ok(riskDimension);
	}
	
	/**
	 * Method for updating risk dimension data
	 * @param dimension
	 * @param riskDimensionDetails
	 * @return
	 */
	@PutMapping("/risk-dimension/{dimension}")
	public ResponseEntity<RiskDimension> updateRiskDimension(@PathVariable String dimension, @RequestBody RiskDimension riskDimensionDetails){
		RiskDimension riskDimension = riskDimensionService.getRiskDimensionByDimension(riskDimensionDetails);	
		riskDimension.setDimension(riskDimensionDetails.getDimension());
		riskDimension.setWeight(riskDimensionDetails.getWeight());
		RiskDimension updatedRiskDimension = riskDimensionService.saveRiskDimension(riskDimension);
		return ResponseEntity.ok(updatedRiskDimension);
	}
	
	/**
	 * Method for deleting risk dimension data
	 * @param dimension
	 * @return
	 */
	@DeleteMapping("/risk-dimension/{dimension}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskDimension(@PathVariable String dimension){
		RiskDimension riskDimension = riskDimensionService.getRiskDimensionById(dimension);
		riskDimensionService.deleteRiskDimension(riskDimension);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}	
}
