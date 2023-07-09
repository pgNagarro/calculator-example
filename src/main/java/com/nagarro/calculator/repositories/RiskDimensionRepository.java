package com.nagarro.calculator.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.RiskDimension;

/**
 * Interface for Risk Dimension repo
 * @author parasgautam
 *
 */
public interface RiskDimensionRepository extends JpaRepository<RiskDimension,String>{
	

	public RiskDimension findByDimension(String name);
	

}
