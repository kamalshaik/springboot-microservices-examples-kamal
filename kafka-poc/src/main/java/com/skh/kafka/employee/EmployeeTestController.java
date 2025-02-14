package com.skh.kafka.employee;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeTestController {
	@Autowired
	private EmployeeProducer empProducerservice;

	@RequestMapping("/publishEmployee")
	public void Hello() {
		
		EmployeePojo employeeObj = new EmployeePojo(new Random().nextInt(),
				java.util.UUID.randomUUID().toString().replaceAll("-", ""), new Random().nextDouble(), new Date());
		
		empProducerservice.publishEmployee(employeeObj);
		
		// java.util.UUID.randomUUID().toString().replaceAll("-", "") for generating random string values.
	}
}