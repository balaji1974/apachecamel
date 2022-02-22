package com.bala.camel.microservices.utils;

import java.math.BigDecimal;

public class Customer1 {
	
	private String orderService;
	private String  description; 
	private BigDecimal   quantityInLiters; 
	//private Date    dateOfOrder;
	
	public String getOrderService() {
		return orderService;
	}
	public void setOrderService(String orderService) {
		this.orderService = orderService;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantityInLiters() {
		return quantityInLiters;
	}
	public void setQuantityInLiters(BigDecimal quantityInLiters) {
		this.quantityInLiters = quantityInLiters;
	}
	@Override
	public String toString() {
		return "Customer1 [orderService=" + orderService + ", description=" + description + ", quantityInLiters="
				+ quantityInLiters + "]";
	}
	

}
