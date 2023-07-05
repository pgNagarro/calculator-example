package com.nagarro.calculator.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.services.CompanyRiskScoreService;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author parasgautam
 * 	 
 * Controller class for Company Risk Score to handle operations
 * on company risk score data
 */
@RestController
@CrossOrigin(origins="*")
public class CompanyRiskScoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyRiskScoreController.class);
	
	
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
	
	/**
	 * Method to get all data in company risk score table
	 * @return
	 */
	@GetMapping("/risk-score")
	public List<CompanyRiskScore> getCompanyRiskScore() {
		return companyRiskScoreService.getAllCompanyRiskScore();
	}
	
	/**
	 * 
	 * @param companyRiskScore
	 * @return
	 */
	@PostMapping("/addRiskScore")	
	public CompanyRiskScore saveRiskScore(@RequestBody CompanyRiskScore companyRiskScore) {
		
		if(!companyRiskScoreService.checkDataIfPresent(companyRiskScore)) {
			logger.error("Company name already present");
		}
		 return companyRiskScoreService.saveRiskScore(companyRiskScore);
	}
	

}
