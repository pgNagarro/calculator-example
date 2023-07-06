package com.nagarro.calculator.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.calculator.models.CompanyRiskScore;

/**
 * Interface for Company Risk Score Service
 * @author parasgautam
 *
 */
public interface CompanyRiskScoreService {
	
	List<CompanyRiskScore> getAllCompanyRiskScore();
	
	void saveRiskScore(CompanyRiskScore companyRiskScore) throws IOException;
	
	CompanyRiskScore getCompanyRiskScoreByCompanyName(String companyName) throws IOException;
	
	CompanyRiskScore updateRiskScore(CompanyRiskScore companyRiskScore);
	
	void deleteCompanyRiskScore(CompanyRiskScore companyRiskScore);
	
	boolean checkDataIfPresent(CompanyRiskScore companyRiskScore);
}
