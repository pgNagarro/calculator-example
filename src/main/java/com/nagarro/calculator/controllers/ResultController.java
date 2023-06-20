package com.nagarro.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {

	@GetMapping("/home")
	public String outputPage(Model model) {
		return "index";
	}

}
