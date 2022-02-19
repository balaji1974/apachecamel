package com.bala.camel.patterns.enterpriseintegrationpatterns.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MutlicastPattern extends RouteBuilder {

	@Override 
	public void configure() throws Exception {
	  
	  from("timer:mytimer?period=10000") // This will create a timer called mytimer
	  .multicast()
	  .to("log:out-log1", "log:out-log2", "log:out-log3"); // This will write data to multiple end points
	  
	}

}
