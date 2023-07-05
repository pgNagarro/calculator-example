package com.nagarro.calculator.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.services.CompanyRiskScoreService;

/**
 * Service Implementation Class for Company Risk Score Service
 * @author parasgautam
 *
 */
@Service
public class CompanyRiskScoreServiceImpl implements CompanyRiskScoreService{

	private static final Logger logger = LoggerFactory.getLogger(CompanyRiskScoreServiceImpl.class);
	
	@Autowired
	private CompanyRiskScoreRepository companyRiskScoreRepository;

	@Override
	public void addCompanyRiskScore(CompanyRiskScore companyRiskScore) {
		logger.info("start : addCompanyRiskScore");
		companyRiskScoreRepository.save(companyRiskScore);
	}

	@Override
	public List<CompanyRiskScore> getAllCompanyRiskScore() {
		logger.info("start : getAllCompanyRiskScore");
		return companyRiskScoreRepository.findAll();
	}
	
	@Override
	public boolean checkDataIfPresent(CompanyRiskScore companyRiskScore) {
		List<CompanyRiskScore> companyRiskScores = companyRiskScoreRepository.findByCompanyName(companyRiskScore.getCompanyName());
		return companyRiskScores.isEmpty();
	}
	
	@Override
	public CompanyRiskScore saveRiskScore(CompanyRiskScore companyRiskScore) {
		return companyRiskScoreRepository.save(companyRiskScore);
	}
	
	/*
	@Override
	public List<CompanyRiskScore> getAllRiskScore() {
		return companyRiskScoreRepository.findAll();
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

	
	
	*/

}
