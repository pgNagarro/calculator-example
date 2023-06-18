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


}
