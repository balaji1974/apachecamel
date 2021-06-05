package com.bala.microservices.apachecameltwo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:my-activemq-queue").
		to("log:${body}");
	}
	
	

}
