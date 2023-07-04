package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.CompanyRiskScore;

public interface CompanyRiskScoreRepository extends JpaRepository<CompanyRiskScore, String>{

	public List<CompanyRiskScore> findByCompanyName(String name);
	
	public void deleteByCompanyName(String name);
}
