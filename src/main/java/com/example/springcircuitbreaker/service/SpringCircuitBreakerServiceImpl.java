package com.example.springcircuitbreaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springcircuitbreaker.constants.SpringCircuitBreakerConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpringCircuitBreakerServiceImpl implements SpringCircuitBreakerService {

	@Autowired
	private CircuitBreakerFactory<?, ?> circuitBreakerFactory;

	@Autowired
	private RetryTemplate retryTemplate;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String getHelloWorldService() {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		return circuitBreaker.run(() -> retryCall(), throwable -> getFallBack());
	}

	private String getHelloWorldServiceExternal() {
		log.info("Calling External API");
		return restTemplate.getForObject(SpringCircuitBreakerConstants.EXTERNAL_API_URL, String.class);
	}

	private String retryCall() {
		return retryTemplate.execute(context -> {
			log.info("Retry Count {}", context.getRetryCount());
			return getHelloWorldServiceExternal();
		});
	}

	private String getFallBack() {
		return SpringCircuitBreakerConstants.SERVICE_NOT_AVAILABLE;
	}

}
