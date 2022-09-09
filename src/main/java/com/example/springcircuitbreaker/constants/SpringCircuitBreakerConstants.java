package com.example.springcircuitbreaker.constants;

public final class SpringCircuitBreakerConstants {

	private SpringCircuitBreakerConstants() {
		throw new IllegalStateException("Constant class");
	}

	public static final String SERVICE_NOT_AVAILABLE = "Hello World Service is not available!";
	public static final String EXTERNAL_API_URL = "http://localhost:8080/";
}
