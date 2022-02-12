package com.bala.camel.microserviceb.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCurrencyExchangeProcessor {
	
	Logger logger=LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);
	
	public void processMessage(CurrencyConverter currencyConverter) {
		logger.info("Do something with the class that is received: ");
		logger.info("For eg. the converstion ration is: "+currencyConverter.getConversionMultiple());
	}

}
