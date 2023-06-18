package com.nagarro.calculator.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.repositories.ScoreCapRepository;
import com.nagarro.calculator.services.ScoreCapService;

@Service
public class ScoreCapServiceImpl implements ScoreCapService{

	@Autowired
	private ScoreCapRepository scoreCapRepository;

	@Override
	public List<ScoreCap> getAllScoreCap() {
		return scoreCapRepository.findAll();
	}

}
