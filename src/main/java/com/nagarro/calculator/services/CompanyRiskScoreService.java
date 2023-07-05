package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.CompanyRiskScore;

/**
 * Interface for Company Risk Score Service
 * @author parasgautam
 *
 */
public interface CompanyRiskScoreService {
	
	List<CompanyRiskScore> getAllCompanyRiskScore();
	
	void addCompanyRiskScore(CompanyRiskScore companyRiskScore);	

//	List<CompanyRiskScore> getAllRiskScore();
//	
	CompanyRiskScore saveRiskScore(CompanyRiskScore companyRiskScore);
//	
//	CompanyRiskScore getRiskScoreByName(CompanyRiskScore companyRiskScore);
//	
//	CompanyRiskScore updateCompanyRiskScore(CompanyRiskScore companyRiskScore);
//	
//	void deleteCompanyRiskScore(CompanyRiskScore companyRiskScore);
//	
	boolean checkDataIfPresent(CompanyRiskScore companyRiskScore);
}
