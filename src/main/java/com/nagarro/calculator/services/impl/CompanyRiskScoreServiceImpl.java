package com.nagarro.calculator.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.services.CompanyRiskScoreService;

@Service
public class CompanyRiskScoreServiceImpl implements CompanyRiskScoreService{

	@Autowired
	private CompanyRiskScoreRepository companyRiskScoreRepository;
	
	
	@Override
	public List<CompanyRiskScore> getAllRiskScore() {
		return companyRiskScoreRepository.findAll();
	}

	@Override
	public CompanyRiskScore saveRiskScore(CompanyRiskScore companyRiskScore) {
		return companyRiskScoreRepository.save(companyRiskScore);
	}

	@Override
	public CompanyRiskScore getRiskScoreByName(CompanyRiskScore companyRiskScore) {
		List<CompanyRiskScore> companyRiskScores = companyRiskScoreRepository.findByCompanyName(companyRiskScore.getCompanyName());
		return companyRiskScores.get(0);
	}

	@Override
	public CompanyRiskScore updateCompanyRiskScore(CompanyRiskScore companyRiskScore) {
		return companyRiskScoreRepository.save(companyRiskScore);
	}

	@Override
	public void deleteCompanyRiskScore(CompanyRiskScore companyRiskScore) {
		companyRiskScoreRepository.deleteById(companyRiskScore.getId());
	}

	@Override
	public boolean checkDataIfPresent(CompanyRiskScore companyRiskScore) {
		List<CompanyRiskScore> companyRiskScores = companyRiskScoreRepository.findByCompanyName(companyRiskScore.getCompanyName());
		return companyRiskScores.isEmpty();
	}

}
