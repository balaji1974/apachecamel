package com.bala.camel.patterns.enterpriseintegrationpatterns.utils;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DynamicRouterBean {
	
	Logger logger=LoggerFactory.getLogger(DynamicRouterBean.class);
	
	int invocations; // Simple int for some logic
	
	public String getNextEndpoint(
				@ExchangeProperties Map<String,String> properties, 
				@Headers Map<String,String> headers, 
				@Body String body
			) {
		invocations++;
		logger.info("{}, {}, {}" , properties, headers, body );
		
		if(invocations%3 ==0)
			return "direct:endpoint1";
		else if(invocations%3 ==1)
			return "direct:endpoint2, direct:endpoint3";
		else return null;
	}
}
