package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:data/json") // Source directory from which file will be read
		.log("${body}") // log the body 
		.to("kafka:jsontopic"); // Sends it to the Kafka topic with the name jsontopic
	}
}
