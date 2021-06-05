package com.bala.microservices.apachecamelone.route;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * Apache camel is a ETL framework for enterprise integration pattern
 * that is used for routing and mediation rules.
 * To make this program work just uncomment the @Component tag
 * 
 */

//@Component
public class MyFirstTimerRouter extends RouteBuilder {
	
	@Autowired
	CurrentTimeBean currentTime;
	
	@Autowired
	LoggingComponent loggingComponent;
	
	
	@Autowired
	SimpleLoggingComponent simpleLoggingComponent;
	
	/*
	 * Transform changes the body of the message 
	 * whereas Processing does not change the body of the message 
	 */
	@Override
	public void configure() throws Exception {
		from("timer:first-timer") // Pick up a message from the timer end point
		//.transform() // This will change the body of the message 
		//.constant("Time now is :"+LocalDateTime.now()) // Convert it to a constant message,
													   // real scenario will be the actual transformation that we need
		
		//.bean("currentTimeBean") // Instead of a constant above lets use a bean to get the latest time dynamically 
		// The below is a better way to do it than a string constant for the bean by using Autowiring
		.bean(currentTime,"getCurrentTime") // The second parameter is the method name of the autowired bean
		.bean(loggingComponent) // Processor that does not change the body 
		.bean(simpleLoggingComponent) // Same as above but with implementation of the Processor interface 
		.to("log:first-timer");  // Send it to an output area, eg. log, database, another message listener etc
	}

}

@Component
class CurrentTimeBean {
	public String getCurrentTime() {
		return "Time now is :"+LocalDateTime.now();
	}
}

@Component
class LoggingComponent {
	Logger logger=LoggerFactory.getLogger(LoggingComponent.class);
	
	public void process(String message) {
		logger.info(message);
	}
}

@Component
class SimpleLoggingComponent implements Processor{
	Logger logger=LoggerFactory.getLogger(LoggingComponent.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info(exchange.getMessage().getBody().toString());
		
	}
}
