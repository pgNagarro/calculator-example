package com.nagarro.calculator.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.repositories.ScoreCapRepository;
import com.nagarro.calculator.services.ScoreCapService;

import lombok.AllArgsConstructor;

/**
 * Service Implementation Class for Score Cap Service
 * @author parasgautam
 *
 */
@Service
@AllArgsConstructor
public class ScoreCapServiceImpl implements ScoreCapService{

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapServiceImpl.class);

	@Autowired
	private ScoreCapRepository scoreCapRepository;
	

	/**
	 * Method to get all score cap data
	 */
	@Override
	public List<ScoreCap> getAllScoreCap() {
		
		logger.info("start : getAllScoreCap");
		return scoreCapRepository.findAll();
	}

	
	/**
	 * Method to save score cap data
	 */
	@Override
	public ScoreCap saveScoreCap(ScoreCap scoreCap) {
		
		logger.info("start : saveScoreCap");
		return scoreCapRepository.save(scoreCap);
		
	}
	
	
	/**
	 * Method to single get score cap data by condition
	 * @throws IOException 
	 */
	@Override
	public ScoreCap getScoreCapByCondition(String condition) throws IOException {
		
		logger.info("start : getScoreCapByCondition");
		
		ScoreCap scoreCaps = scoreCapRepository.findByCondition(condition);
		
		if(scoreCaps==null) {
			throw new IOException("Score cap data not found");
		}
		return scoreCaps;
		
	}
	
	/**
	 * Method to delete score cap data
	 */
	@Override
	public void deleteScoreCap(ScoreCap scoreCap) {
		
		logger.info("start : deleteScoreCap");
		scoreCapRepository.deleteById(scoreCap.getCondition());	
		
	}

}
