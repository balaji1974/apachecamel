package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ReusableLogRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		/*
		 * This will move file from a source folder to a destination folder 
		 * and will also log all the files with extension as XML 
		 * 
		 */
		from("file:data/input") // Source directory 
		.routeId("XML-CHOICE-ROUTE") // This will give a route id to our route 
		.transform().body(String.class) // We need to transform the body before we can parse and read its contents 
		.choice()
			.when(simple("${file:ext} ends with 'xml'")) // if the file extension ends with XML 
				.log("This is a XML file") // Log it as xml file 
			.when(simple("${body} contains  'USD'")) // if the file body contains USD and not an XML file
				.log("This is not a XML file but contains USD") // Log it as xml file 
			.otherwise() // else statment 
				.log("This is not a XML file") // Log it as not an xml file 
		.end()
		.to("direct://log-data")
		.to("file:data/output"); // Destination directory to which the file will be moved 
		
		
		// this will create a reusable component block that can be used in the entire camel context 
		from("direct:log-data")
		.log("${messageHistory} ${file:absolute.path}")
		.log("${file:name} ${file:ext} ")
		; 
	}

}
