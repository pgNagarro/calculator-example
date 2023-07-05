package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.CompanyRiskScore;

/**
 * Interface for company risk score repo
 * @author parasgautam
 *
 */
public interface CompanyRiskScoreRepository extends JpaRepository<CompanyRiskScore, String>{

	public List<CompanyRiskScore> findByCompanyName(String name);
	
	public void deleteByCompanyName(String name);
}
