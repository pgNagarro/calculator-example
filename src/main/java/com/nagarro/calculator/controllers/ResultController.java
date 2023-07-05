package com.nagarro.calculator.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.calculator.models.Result;
import com.nagarro.calculator.services.ResultService;

/**
 * 
 * @author parasgautam
 *
 * Controller class for output table which
 * calculates and gets output
 */
@RestController
@CrossOrigin(origins="*")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	/**
	 * API Method for getting result
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/result")
	public List<Result> displayResult() throws IOException {
		resultService.calculateResult();
		return resultService.getResult();
	}
}
