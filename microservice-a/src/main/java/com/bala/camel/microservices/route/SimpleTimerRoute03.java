package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bala.camel.microservices.utils.CurrentTimeBean;


//@Component
public class SimpleTimerRoute03 extends RouteBuilder {
	
	@Autowired
	CurrentTimeBean currentTimeBean;

	@Override
	public void configure() throws Exception {
		
		// Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		from("timer:in-timer") // This will create a timer called in-timer
		.log("${body}") // This is processing the message where the body is not changed
		.transform().constant("This is a constant message")
		.log("${body}") // This is transformation where the body is changed
		// This will dynamically fetch date from the bean for each instance fired by the timer 
		.bean(currentTimeBean, "getCurrentTime") // Bean name and method name as parameters 
		.log("${body}") // This is transformation where the body is changed
		.to("log:out-log"); // This will write the out-log data to the log file 
	}

}


