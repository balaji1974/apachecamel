package com.bala.camel.microservices.utils;





import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCustomer1Transformer {
	
	private final BigDecimal CONVERSION_FACTOR= new BigDecimal(0.2641722);
	
	Logger logger=LoggerFactory.getLogger(MyCustomer1Transformer.class);
	
	public BaseInputFormat processMessage(Customer1 customer1) {
		
		logger.info("******** Transformation is done here **************");
		BaseInputFormat customer2=new BaseInputFormat();
		customer2.setOrderService(customer1.getOrderService());
		customer2.setDescription(customer1.getDescription());
		customer2.setQuantityInGallons(customer1.getQuantityInLiters().multiply(CONVERSION_FACTOR).setScale(2, RoundingMode.HALF_EVEN));
		//logger.info(customer2.toString());
		return customer2;
	}

}
