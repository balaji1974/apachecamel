package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonFileToApacheMQSender extends RouteBuilder{ 
	@Override
	public void configure() throws Exception {
		from("file:data/json") // Source directory from which file will be read
		.log("${body}")
		.to("activemq:my-json-reader"); // Sends it to the ActiveMQ queue my-json-reader
	}
}
