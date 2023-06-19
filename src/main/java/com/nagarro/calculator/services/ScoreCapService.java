package com.nagarro.calculator.services;

import java.util.List;


import com.nagarro.calculator.models.ScoreCap;

public interface ScoreCapService {
	
	List<ScoreCap> getAllScoreCap();

	ScoreCap saveScoreCap(ScoreCap scoreCap);
	
	ScoreCap getScoreCapById(ScoreCap scoreCap);
	
	ScoreCap updateScoreCap(ScoreCap scoreCap);
	
	void deleteScoreCap(ScoreCap scoreCap);
	
}
