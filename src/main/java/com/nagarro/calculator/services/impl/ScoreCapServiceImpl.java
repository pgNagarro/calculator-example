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

	@Override
	public ScoreCap saveScoreCap(ScoreCap scoreCap) {
		return scoreCapRepository.save(scoreCap);
	}

	@Override
	public ScoreCap getScoreCapById(ScoreCap scoreCap) {
		List<ScoreCap> scoreCaps = scoreCapRepository.findByCondition(scoreCap.getCondition());
		return scoreCaps.get(0);
	}

	@Override
	public ScoreCap updateScoreCap(ScoreCap scoreCap) {
		return scoreCapRepository.save(scoreCap);
	}

	@Override
	public void deleteScoreCap(ScoreCap scoreCap) {
		scoreCapRepository.deleteById(scoreCap.getCondition());	
	}

	@Override
	public boolean checkDataIfPresent(ScoreCap scoreCap) {
		List<ScoreCap> scoreCaps = scoreCapRepository.findByCondition(scoreCap.getCondition());
		return scoreCaps.isEmpty();
	}

}
