package com.bala.camel.patterns.enterpriseintegrationpatterns.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RoutingSlipPattern extends RouteBuilder {

	@Override 
	public void configure() throws Exception {

		// This is the routing slip which carries information as to which endpoint the data must be routed to 
		String routingSlip="direct:endpoint2, direct:endpoint3";

		from("timer:mytimer?period=10000") // This will create a timer called mytimer
		.transform().constant("This is my constant message")
		.routingSlip(simple(routingSlip)); // This will configure the routing slip based on the passed value 


		// Below are different endpoint configurations 
		from("direct:endpoint1")
		.to("log:endpoint1");

		from("direct:endpoint2")
		.to("log:endpoint2");

		from("direct:endpoint3")
		.to("log:endpoint3");

	}

}
