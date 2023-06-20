package com.nagarro.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditOrAddController {

	@GetMapping("/edit-or-add")
	public String editOrAdd(Model model) {
		return "editOrAdd";
	}
	
}
