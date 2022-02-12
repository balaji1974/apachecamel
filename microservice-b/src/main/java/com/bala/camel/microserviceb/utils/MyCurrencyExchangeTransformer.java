package com.bala.camel.microserviceb.utils;



import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCurrencyExchangeTransformer {
	
	Logger logger=LoggerFactory.getLogger(MyCurrencyExchangeTransformer.class);
	
	public CurrencyConverter processMessage(CurrencyConverter currencyConverter) {
		
		logger.info("Do some transformation with the class that is received and return something back ");
		currencyConverter.setConversionMultiple(currencyConverter.getConversionMultiple().multiply(BigDecimal.TEN));
		return currencyConverter;
	}

}
