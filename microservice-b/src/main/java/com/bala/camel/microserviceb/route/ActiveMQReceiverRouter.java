package com.bala.camel.microserviceb.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQReceiverRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("activemq:my-activemq-queue") // Read the messages fom an activemq queue  my-activemq-queue
		.to("log:received-msg-from-activemq"); // Write it to the output log
	}

}
