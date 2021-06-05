package com.bala.microservices.apachecamelone.route;

import org.apache.camel.builder.RouteBuilder;

/*
 * This example is used for moving files instantly between folders 
 * Very useful in production environments 
 * Uncomment @Component if you need this to work
 */

//@Component
public class MyFileRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:files/input")
		.log("${body}")
		.to("file:files/output");
		
	}

}
