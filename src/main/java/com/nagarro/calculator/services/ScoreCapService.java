package com.nagarro.calculator.services;

import java.util.List;


import com.nagarro.calculator.models.ScoreCap;

public interface ScoreCapService {
	
	List<ScoreCap> getAllScoreCap();
	
	void addScoreCap(ScoreCap scoreCaps);
	
	ScoreCap findScoreCap(String condition);
	
	ScoreCap saveScoreCap(ScoreCap scoreCap);
	
//	List<ScoreCap> getAllScoreCap();
//
	
//	
	ScoreCap getScoreCapById(ScoreCap scoreCap);
	
	ScoreCap getScoreCapByCondition(String condition);
//	
//	ScoreCap updateScoreCap(ScoreCap scoreCap);
//	
	void deleteScoreCap(ScoreCap scoreCap);
//	
//	boolean checkDataIfPresent(ScoreCap scoreCap);
	
}
