package com.nagarro.calculator.services;

import java.io.IOException;
import java.util.List;


import com.nagarro.calculator.models.ScoreCap;

/**
 * Interface for Score Cap Service
 * @author parasgautam
 *
 */
public interface ScoreCapService {
	
	List<ScoreCap> getAllScoreCap();
		
	ScoreCap saveScoreCap(ScoreCap scoreCap);

	ScoreCap getScoreCapByCondition(String condition) throws IOException;

	void deleteScoreCap(ScoreCap scoreCap);

}
