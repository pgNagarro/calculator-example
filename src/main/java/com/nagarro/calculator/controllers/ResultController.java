package com.nagarro.calculator.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nagarro.calculator.models.Result;
import com.nagarro.calculator.models.OutputValues;
import com.nagarro.calculator.services.ResultService;


@Controller
@CrossOrigin(origins="*")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@GetMapping("/result")
	public @ResponseBody List<Result> displayResult() throws IOException {
		resultService.calculateResult();
		return resultService.getResult();
	}
	
	@GetMapping("/home")
	public String outputPage(Model model) {
	//	List<OutputValues> list = resultService.getResult().get(0).getValues();
		model.addAttribute("result", resultService.getResult());
		//model.addAttribute("outputValues",list);
		return "index";
	}
	

}
