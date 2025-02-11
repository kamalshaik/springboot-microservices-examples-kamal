package com.skh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("mmt")
public class MgtController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/display")
	public String display() {
		
		System.out.println("Inside --> MgtController --> display() method. " + env.getProperty("server.port"));
		
		return restTemplate.getForObject("http://USER-APP/users/display", String.class);
	}
	
	@GetMapping("/")
	public String test() {
		return restTemplate.getForObject("http://USER-APP/", String.class);
	}

}
