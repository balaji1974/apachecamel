package com.bala.camel.patterns.enterpriseintegrationpatterns.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SplitterPattern extends RouteBuilder {

	@Override 
	public void configure() throws Exception {
	  
	 from("file:data/csv") // Source directory  
	 .unmarshal().csv() // This will process the input file as a CSV file 
	  .split(body())
	  .to("activemq:my-csv-queue"); // Sends it to the ActiveMQ queue my-csv-queue 
	  //.to("log:test-log");
	}
}
