package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nagarro.calculator.models.RiskScoreLevel;

public interface RiskScoreLevelRepository extends JpaRepository<RiskScoreLevel, String>{

	public List<RiskScoreLevel> findByScore(String name);
}
