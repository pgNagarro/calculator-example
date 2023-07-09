package com.nagarro.calculator.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.RiskCalc;

/**
 * Interface for Risk Calculation Logic repo
 * @author parasgautam
 *
 */
public interface RiskCalcRepository extends JpaRepository<RiskCalc, String>{

	public RiskCalc findByElementName(String name);
}
