package com.bala.camel.microservices.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bala.camel.microservices.utils.BaseInputFormat;


@RestController
@RequestMapping("/ajex")
public class TestController {
	
	@PostMapping("/order")
	public BaseInputFormat createOrder(@RequestBody BaseInputFormat body) {
		System.out.println("The payload received in the target url after transformation is : "+body);
		return body;
	}

}
