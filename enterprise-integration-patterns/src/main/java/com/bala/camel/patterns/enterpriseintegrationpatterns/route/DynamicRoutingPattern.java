package com.bala.camel.patterns.enterpriseintegrationpatterns.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bala.camel.patterns.enterpriseintegrationpatterns.utils.DynamicRouterBean;

@Component
public class DynamicRoutingPattern extends RouteBuilder {
	
	@Autowired
	DynamicRouterBean dynamicRouterBean;

	@Override 
	public void configure() throws Exception {
		
		//getContext().setTracing(true); 
		//errorHandler(deadLetterChannel("activemq:dead-letter-queue"));
		
		from("timer:mytimer?period=10000") // This will create a timer called mytimer
		.transform().constant("This is my constant message")
		.dynamicRouter(method(dynamicRouterBean)); // This will configure the routing slip based on the passed value 

		
		
		// Below are different endpoint configurations 
		from("direct:endpoint1")
		.wireTap("log:wiretap-log")
		.to("log:endpoint1");

		from("direct:endpoint2")
		.to("log:endpoint2");

		from("direct:endpoint3")
		.to("log:endpoint3");

	}

}
