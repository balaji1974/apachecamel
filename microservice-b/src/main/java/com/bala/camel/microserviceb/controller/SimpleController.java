package com.bala.camel.microserviceb.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bala.camel.microserviceb.utils.CurrencyConverter;

@RestController
public class SimpleController {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConverter currencyConverter(@PathVariable String from, @PathVariable String to) {
		return new CurrencyConverter(10010L, from, to, BigDecimal.TEN);
	}

}
