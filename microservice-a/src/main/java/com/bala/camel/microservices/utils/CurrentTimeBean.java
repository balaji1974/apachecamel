package com.bala.camel.microservices.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CurrentTimeBean {
	public String getCurrentTime() {
		return "This is a dynamic message using transformers :"+LocalDateTime.now();
	}
}