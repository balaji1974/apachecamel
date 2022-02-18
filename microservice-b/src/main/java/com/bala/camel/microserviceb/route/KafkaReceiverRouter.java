package com.bala.camel.microserviceb.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaReceiverRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("kafka:jsontopic") // Read the messages fom a kafka topic named jsontopic
		.to("log:received-msg-from-kafka"); // Write it to the output log
	}

}
