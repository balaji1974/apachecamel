package com.bala.camel.microservices.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		/*
		 * This will move files from a source folder to a destination folder 
		 * 
		 */
		// Source directory 
		from("file:data/input")
		// Body of file that is moved will be printed in the log 
		.log("${body}")
		// Destination directory to which the file will be moved 
		.to("file:data/output");
	}

}
