package com.bala.camel.microserviceb.route;

import java.util.ArrayList;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.bala.camel.microserviceb.utils.CurrencyConverter;

//@Component
public class ActiveMQExcelReceiver extends RouteBuilder{
	

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("activemq:my-csv-queue") // Read the messages from an activemq queue my-csv-queue
		.to("log:excel-activemq"); // Write it to the output log
	}

}
