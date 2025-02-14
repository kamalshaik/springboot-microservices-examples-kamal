package com.skh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
//	@Value("${topicName}") // getting value from .properties file.
	@Value("#{pojoConfig.topic ?: ' '}") // getting value from other class field.
	private String TOPIC;
	@Autowired
	private KafkaTemplate<String, Employee> kafkaTemplate;
	
	public String Test( String s) {
		
		kafkaTemplate.send(TOPIC, new Employee().getEmployees().get(0));
		return s;
	}
	

}