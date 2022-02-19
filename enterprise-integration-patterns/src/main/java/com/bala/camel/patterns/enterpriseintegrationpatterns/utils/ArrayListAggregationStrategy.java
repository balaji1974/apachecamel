package com.bala.camel.patterns.enterpriseintegrationpatterns.utils;

import java.util.ArrayList;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class ArrayListAggregationStrategy implements AggregationStrategy {
	
	// Below is the how the strategy works 
	// (null), (1) -> old value,new value 
	// (null, 1), (2) -> old value,new value 
	// (null, 1, 2), (3) -> old value,new value 

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		Object newBody = newExchange.getIn().getBody();
        ArrayList<Object> list = null;
        if (oldExchange == null) {
            list = new ArrayList<Object>();
            list.add(newBody);
            newExchange.getIn().setBody(list);
            return newExchange;
        } 
        else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            list.add(newBody);
            return oldExchange;
        }
	}

}
