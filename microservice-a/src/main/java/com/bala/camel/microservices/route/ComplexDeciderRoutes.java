package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bala.camel.microservices.utils.DeciderBean;

//@Component
public class ComplexDeciderRoutes extends RouteBuilder {
	
	@Autowired
	DeciderBean deciderBean;

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
			.when(method(deciderBean)) // call a method that will handle complex logic 
				.log("This is not a XML file but contains USD") // Log it as xml file 
			.otherwise() // else statment 
				.log("This is not a XML file") // Log it as not an xml file 
		.end()
		.to("file:data/output"); // Destination directory to which the file will be moved 
		
	}
}
