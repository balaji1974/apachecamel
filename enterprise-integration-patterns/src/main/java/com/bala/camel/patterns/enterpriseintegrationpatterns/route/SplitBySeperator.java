package com.bala.camel.patterns.enterpriseintegrationpatterns.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SplitBySeperator extends RouteBuilder {

	@Override 
	public void configure() throws Exception {
	  
	 from("file:data/csv") // Source directory  
	 .convertBodyTo(String.class) // Convert the body to a String
	  .split(body(), ",") // Split by separator 
	  .to("activemq:split-seperator-queue"); // Sends it to the ActiveMQ split-seperator-queue 
	  //.to("log:test-log");
	}
}
