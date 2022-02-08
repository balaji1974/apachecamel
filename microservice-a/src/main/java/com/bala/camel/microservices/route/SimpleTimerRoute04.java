package com.bala.camel.microservices.route;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bala.camel.microservices.utils.CurrentTimeBean;
import com.bala.camel.microservices.utils.MyOwnProcessCustomBean;


//@Component
public class SimpleTimerRoute04 extends RouteBuilder {
	
	@Autowired
	CurrentTimeBean currentTimeBean;

	@Override
	public void configure() throws Exception {
		
		// Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		from("timer:in-timer") // This will create a timer called in-timer
		// Set some constant message to be processed - real time will be dynamic 
		.transform().constant("This is a constant message using transformers :" +LocalDateTime.now())
		// Here you can process any data that you want using the process message  
		.process(new MyOwnProcessCustomBean())
		.to("log:out-log"); // This will write the out-log data to the log file 
	}

}


