package com.nagarro.calculator.services;

import java.util.List;

import com.nagarro.calculator.models.CompanyRiskScore;

public interface CompanyRiskScoreService {

	List<CompanyRiskScore> getAllRiskScore();
	
	CompanyRiskScore saveRiskScore(CompanyRiskScore companyRiskScore);
	
	CompanyRiskScore getRiskScoreByName(CompanyRiskScore companyRiskScore);
	
	CompanyRiskScore updateCompanyRiskScore(CompanyRiskScore companyRiskScore);
	
	void deleteCompanyRiskScore(CompanyRiskScore companyRiskScore);
}
