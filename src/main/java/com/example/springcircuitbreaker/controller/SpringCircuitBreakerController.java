package com.example.springcircuitbreaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcircuitbreaker.service.SpringCircuitBreakerService;

@RestController
public class SpringCircuitBreakerController {

	@Autowired
	SpringCircuitBreakerService springCircuitBreakerService;

	@GetMapping("/cbr")
	public String getHelloWorldService() {
		return springCircuitBreakerService.getHelloWorldService();
	}
}
