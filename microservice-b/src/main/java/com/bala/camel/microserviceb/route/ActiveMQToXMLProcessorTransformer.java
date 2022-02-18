package com.bala.camel.microserviceb.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bala.camel.microserviceb.utils.CurrencyConverter;
import com.bala.camel.microserviceb.utils.MyCurrencyExchangeProcessor;
import com.bala.camel.microserviceb.utils.MyCurrencyExchangeTransformer;

//@Component
public class ActiveMQToXMLProcessorTransformer extends RouteBuilder{
	
	@Autowired
	MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
	
	@Autowired
	MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

	@Override
	public void configure() throws Exception {
		from("activemq:my-xml-reader") // Read the messages fom an activemq queue  my-json-reader
		.unmarshal()
		.jacksonxml(CurrencyConverter.class) // This will read the message body in xml format and convert it into java class file
		.bean(myCurrencyExchangeProcessor)
		.bean(myCurrencyExchangeTransformer)
		.to("log:received-msg-from-activemq"); // Write it to the output log
	}

}
