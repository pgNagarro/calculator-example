package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nagarro.calculator.models.RiskScoreLevel;

/**
 * Interface for Risk Score Level repo
 * @author parasgautam
 *
 */
public interface RiskScoreLevelRepository extends JpaRepository<RiskScoreLevel, String>{

	public List<RiskScoreLevel> findByScore(String name);
}
