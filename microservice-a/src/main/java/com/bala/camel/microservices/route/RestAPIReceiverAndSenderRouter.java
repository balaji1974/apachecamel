package com.bala.camel.microservices.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.bala.camel.microservices.utils.Customer1;
import com.bala.camel.microservices.utils.BaseInputFormat;
import com.bala.camel.microservices.utils.MyCustomer1Transformer;

@Component
public class RestAPIReceiverAndSenderRouter extends RouteBuilder{
	
	/*
	 * Please refer to the application.properties file for the 
	 * following 2 important settings that are needed 
	 * camel.servlet.mapping.context-path=/aj-ex/*
	 * camel.component.servlet.servlet-name=CamelServlet
	 * 
	 */
	
	@Autowired
	MyCustomer1Transformer myCustomer1Transformer;

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "REST API for processing Order")
        .apiProperty("api.version", "1.0")
        .apiProperty("cors", "true")
        .apiContextRouteId("doc-api")
        .bindingMode(RestBindingMode.json);
		
		rest("/order/process")
		
		// First customer
		.get("/customer1").description("Process order for customer1")	
		.type(Customer1.class)
		.route().routeId("orders-api1")
		.log("The payload received is : ${body}")
		.bean(myCustomer1Transformer)
		.marshal().json()
		.multicast()
		.to("log:test-log" , "direct:ddsService")
		//.removeHeaders("CamelHttp*")
		.endRest()
		
		// Second customer 
		.get("/customer2").description("Process order for customer2")	
		.route().routeId("orders-api2")
		//.bean(OrderService.class, "generateOrder")
		//.to("direct:fetchProcess");
		.to("log:testlog2")
		.endRest();
		
		
		from("direct:ddsService") 
		.process(new Processor() {              
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getMessage().setHeader(Exchange.HTTP_METHOD, HttpMethod.POST);
                exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/json");
                exchange.getMessage().setHeader("Accept", "application/json");
            }
        })
		.to("http://localhost:8080/ajex/order?bridgeEndpoint=true")
		.unmarshal(new JacksonDataFormat(BaseInputFormat.class));
		//.log("After making the rest call: ${body}"); // log the body '
	}
	
	
}