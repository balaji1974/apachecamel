package com.bala.camel.microservices.utils;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyOwnProcessCustomBean implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange.getMessage().getBody());
	}
}
