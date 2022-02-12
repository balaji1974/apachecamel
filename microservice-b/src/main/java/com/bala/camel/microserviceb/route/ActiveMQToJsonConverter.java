package com.bala.camel.microserviceb.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.bala.camel.microserviceb.utils.CurrencyConverter;

@Component
public class ActiveMQToJsonConverter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:my-json-reader") // Read the messages fom an activemq queue  my-json-reader
		.unmarshal().json(JsonLibrary.Jackson, CurrencyConverter.class) // This will read the message body in json format and convert it into java class file
		.to("log:received-msg-from-activemq"); // Write it to the output log
	}

}
