package com.nagarro.calculator.repositories;


/**
 * Interface for Score Cap repo
 * @author parasgautam
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.ScoreCap;

public interface ScoreCapRepository extends JpaRepository<ScoreCap, String>{
	
	public ScoreCap findByCondition(String condition);
}
