package com.bala.camel.microservices.route;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SimpleTimerRoute01 extends RouteBuilder {

	@Override 
	public void configure() throws Exception {
	  
	  // Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
	  from("timer:in-timer") // This will create a timer called in-timer 
	  // This is a constant transformer writing a constant time for all the messages
	  .transform().constant("This is a constant message using transformers :" +LocalDateTime.now()) .to("log:out-log"); // This will write the out-log data to the log file 
	}

}
