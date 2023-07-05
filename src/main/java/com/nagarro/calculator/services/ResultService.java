package com.nagarro.calculator.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.calculator.models.Result;

/**
 * Interface for Result Service
 * @author parasgautam
 *
 */
public interface ResultService {
	
	List<Result> getResult();
	
	void addResult(Result result);
	
	void calculateResult() throws IOException;
}
