package com.bala.camel.microservices.utils;

import java.math.BigDecimal;

public class BaseInputFormat {
	
	private String orderService;
	private String description; 
	private BigDecimal quantityInGallons; 
	
	
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
	public BigDecimal getQuantityInGallons() {
		return quantityInGallons;
	}
	public void setQuantityInGallons(BigDecimal quantityInGallons) {
		this.quantityInGallons = quantityInGallons;
	}
	@Override
	public String toString() {
		return "DDSBaseFormat [orderService=" + orderService + ", description=" + description + ", quantityInGallons="
				+ quantityInGallons + "]";
	}
	
	

}
