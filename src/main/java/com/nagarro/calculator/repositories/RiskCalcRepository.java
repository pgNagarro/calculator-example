package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.RiskCalc;

public interface RiskCalcRepository extends JpaRepository<RiskCalc, String>{

	public List<RiskCalc> findByElementName(String name);
}
