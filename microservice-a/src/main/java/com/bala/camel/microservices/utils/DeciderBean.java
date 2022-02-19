package com.bala.camel.microservices.utils;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeciderBean {
	Logger logger = LoggerFactory.getLogger(DeciderBean.class);
	
	public boolean someCondition(@Body String body, @Headers Map<String, String> headers, @ExchangeProperties Map<String, String> exchangeProps) {
		logger.info( "Decider Bean body {} ", body);
		logger.info( "Decider Bean headers {} ", headers);
		logger.info( "Decider Bean exchange properties {} ", exchangeProps);
		return true;
	}
}