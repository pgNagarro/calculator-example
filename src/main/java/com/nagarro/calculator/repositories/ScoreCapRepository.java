package com.nagarro.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.ScoreCap;

public interface ScoreCapRepository extends JpaRepository<ScoreCap, String>{

	public List<ScoreCap> findByCondition(String name);
}
