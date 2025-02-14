package com.skh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    	@Autowired
	private Producer producer;	
	
	@RequestMapping("/service")
	public String Hello() {
		producer.Test("Hellooo");
		return "hello";		
	}		
}