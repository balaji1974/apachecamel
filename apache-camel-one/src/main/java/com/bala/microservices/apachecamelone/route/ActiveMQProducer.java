package com.bala.microservices.apachecamelone.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQProducer extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:active-mq-timer?period=10000") // A timer that gets triggered every 10 seconds - 10000 milliseconds
		.transform().constant("My ActiveMQ constant message") // Sends a constant message 
		.log("${body}")
		.to("activemq:my-activemq-queue"); // Sends it to the ActiveMQ queue my-activemq-queue - This must be created first in activemq
	}

}
