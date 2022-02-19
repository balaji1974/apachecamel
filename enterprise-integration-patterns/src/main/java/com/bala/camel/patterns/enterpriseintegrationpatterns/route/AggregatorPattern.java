package com.bala.camel.patterns.enterpriseintegrationpatterns.route;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.bala.camel.patterns.enterpriseintegrationpatterns.utils.ArrayListAggregationStrategy;
import com.bala.camel.patterns.enterpriseintegrationpatterns.utils.CurrencyConverter;


//@Component
public class AggregatorPattern extends RouteBuilder {

	
	@Override 
	public void configure() throws Exception {
	  
		from("file:data/input") // Source directory  
		.unmarshal().json(JsonLibrary.Jackson, CurrencyConverter.class) // This will process the input file as a json file and unmarshall it to the CurrencyConverter.class 
		.aggregate(simple("${body.to}"), new ArrayListAggregationStrategy()) // Aggregate the body based on the 'to' tag 
		.completionSize(3) // Aggregate 3 messages together 
		.to("log:aggregator-log"); // Sends it to a logfile aggregator-log 
	}
}
