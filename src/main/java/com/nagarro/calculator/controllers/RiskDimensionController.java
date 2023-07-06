package com.nagarro.calculator.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.calculator.models.RiskDimension;
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
	
	
	/**
	 * Method to get all risk dimension data
	 * @return
	 */
	@GetMapping("/risk-dimension")
	public ResponseEntity<List<RiskDimension>> getRiskDimension(){
		
		logger.info("Request received for fetching all risk dimension data");
		
		List<RiskDimension> riskDimensionList = riskDimensionService.getAllRiskDimension();
		
		logger.info("Request completed for fetching all risk dimension data");
		
		return ResponseEntity.ok(riskDimensionList);
		
	}
	
	/**
	 * Method for saving risk dimension data
	 * @param riskDimension
	 * @return
	 */
	@PostMapping("/addRiskDimension")
	public ResponseEntity<RiskDimension> saveRiskDimension(@RequestBody RiskDimension riskDimension) {
		
		logger.info("Request received for risk dimension data");
		
		RiskDimension newRiskDimension = riskDimensionService.saveRiskDimension(riskDimension);
		
		logger.info("Request completed for risk dimension data");
		
		return ResponseEntity.ok(newRiskDimension);
		
	}
	
	/**
	 * Method for getting single risk dimension data by dimension
	 * @param dimension
	 * @return
	 */
	@GetMapping("/risk-dimension/{dimension}")
	public ResponseEntity<RiskDimension> getRiskDimensionByDimension(@PathVariable String dimension){
		
		logger.info("Request received for getting single risk dimension");
		
		RiskDimension riskDimension;
		
		try {
			
			riskDimension = riskDimensionService.getRiskDimensionById(dimension);
			
			logger.info("Request completed for getting single risk dimension");
			
			return ResponseEntity.ok(riskDimension);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}
		
	}
	
	/**
	 * Method for updating risk dimension data
	 * @param dimension
	 * @param riskDimensionDetails
	 * @return
	 */
	@PutMapping("/risk-dimension/{dimension}")
	public ResponseEntity<RiskDimension> updateRiskDimension(@PathVariable String dimension, @RequestBody RiskDimension riskDimensionDetails){
		
		logger.info("Request received for updating risk dimension data");
		
		RiskDimension riskDimension;
		
		try {
			
			riskDimension = riskDimensionService.getRiskDimensionById(riskDimensionDetails.getDimension());
			
			riskDimension.setDimension(riskDimensionDetails.getDimension());
			riskDimension.setWeight(riskDimensionDetails.getWeight());
			RiskDimension updatedRiskDimension = riskDimensionService.saveRiskDimension(riskDimension);
			
			logger.info("Request completed for updating risk dimension data");
			
			return ResponseEntity.ok(updatedRiskDimension);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}	
		
		
	}
	
	/**
	 * Method for deleting risk dimension data
	 * @param dimension
	 * @return
	 */
	@DeleteMapping("/risk-dimension/{dimension}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskDimension(@PathVariable String dimension){
		
		logger.info("Request received for deleting risk dimension");
		
		RiskDimension riskDimension;
		
		try {
			
			riskDimension = riskDimensionService.getRiskDimensionById(dimension);
			
			riskDimensionService.deleteRiskDimension(riskDimension);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request completed for deleting risk dimension");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			
			return ResponseEntity.ok(response);
		}	
	}	
}
