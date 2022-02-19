package com.bala.camel.microserviceb.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQStringSeperatorReceiver extends RouteBuilder{
	

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("activemq:split-seperator-queue") // Read the messages from an activemq queue split-seperator-queue
		.to("log:excel-activemq"); // Write it to the output log
	}

}
