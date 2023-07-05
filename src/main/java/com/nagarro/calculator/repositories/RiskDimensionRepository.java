package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.RiskDimension;

/**
 * Interface for Risk Dimension repo
 * @author parasgautam
 *
 */
public interface RiskDimensionRepository extends JpaRepository<RiskDimension,String>{
	

	public List<RiskDimension> findByDimension(String name);
	

}
