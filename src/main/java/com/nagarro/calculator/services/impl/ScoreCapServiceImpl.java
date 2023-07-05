package com.nagarro.calculator.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.repositories.ScoreCapRepository;
import com.nagarro.calculator.services.ScoreCapService;

/**
 * Service Implementation Class for Score Cap Service
 * @author parasgautam
 *
 */
@Service
public class ScoreCapServiceImpl implements ScoreCapService{

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapServiceImpl.class);

	@Autowired
	private ScoreCapRepository scoreCapRepository;
	
	@Override
	public void addScoreCap(ScoreCap scoreCaps) {
		scoreCapRepository.save(scoreCaps);
	}

	@Override
	public List<ScoreCap> getAllScoreCap() {
		return scoreCapRepository.findAll();
	}

	@Override
	public ScoreCap findScoreCap(String condition) {
		logger.info("start : finScoreCap");
		return scoreCapRepository.findByCondition(condition);
	}
	
	@Override
	public ScoreCap saveScoreCap(ScoreCap scoreCap) {
		return scoreCapRepository.save(scoreCap);
	}
	
	@Override
	public ScoreCap getScoreCapById(ScoreCap scoreCap) {
		ScoreCap scoreCaps = scoreCapRepository.findByCondition(scoreCap.getCondition());
		return scoreCaps;
	}
	
	@Override
	public ScoreCap getScoreCapByCondition(String condition) {
		ScoreCap scoreCaps = scoreCapRepository.findByCondition(condition);
		return scoreCaps;
	}
	
	@Override
	public void deleteScoreCap(ScoreCap scoreCap) {
		scoreCapRepository.deleteById(scoreCap.getCondition());	
	}
/*
	@Override
	public List<ScoreCap> getAllScoreCap() {
		return scoreCapRepository.findAll();
	}

	

	

	@Override
	public ScoreCap updateScoreCap(ScoreCap scoreCap) {
		return scoreCapRepository.save(scoreCap);
	}

	

	@Override
	public boolean checkDataIfPresent(ScoreCap scoreCap) {
		List<ScoreCap> scoreCaps = scoreCapRepository.findByCondition(scoreCap.getCondition());
		return scoreCaps.isEmpty();
	}
	*/

}
