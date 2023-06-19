package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.RiskDimension;

public interface RiskDimensionRepository extends JpaRepository<RiskDimension,String>{
	

	public List<RiskDimension> findByDimension(String name);
	

}
