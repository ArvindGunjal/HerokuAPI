package com.heroku.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/getMessage")
	public String getMessageArvind() {
		
		return "Hello Arvind";
		
	}

	@GetMapping("/")
	public String getMessage() {
		
		return "Hello World";
		
	}
	
	
}
