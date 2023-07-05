package com.nagarro.calculator.services;

import java.util.List;


import com.nagarro.calculator.models.ScoreCap;

/**
 * Interface for Score Cap Service
 * @author parasgautam
 *
 */
public interface ScoreCapService {
	
	List<ScoreCap> getAllScoreCap();
	
	void addScoreCap(ScoreCap scoreCaps);
	
	ScoreCap findScoreCap(String condition);
	
	ScoreCap saveScoreCap(ScoreCap scoreCap);
	
	ScoreCap getScoreCapById(ScoreCap scoreCap);
	
	ScoreCap getScoreCapByCondition(String condition);

	void deleteScoreCap(ScoreCap scoreCap);
//	
//	boolean checkDataIfPresent(ScoreCap scoreCap);
	
}
