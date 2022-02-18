package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestAPIConsumerRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		restConfiguration().host("localhost").port(8000); // This will configure rest host and port that needs to be called
		
		from("timer:rest-api-timer?period=10000") // Make a call on a timer every 10 seconds
		//.log("Before making the call: ${body}") // log the body 
		.setHeader("from", ()->"USD") // To set the REST API parameters dynamically 
		.setHeader("to", ()->"INR") // To set the REST API parameters dynamically 
		.to("rest:get:/currency-exchange/from/{from}/to/{to}") // Make a rest API call on the GET method and the url specified
		.log("After making the call: ${body}"); // log the body 
	}
}
