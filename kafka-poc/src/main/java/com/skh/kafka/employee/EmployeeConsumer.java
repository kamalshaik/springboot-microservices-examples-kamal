package com.skh.kafka.employee;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {
	// NOT WRKING.
    
	//@Value("#{pojoConfig.topic ?: ' '}")
//	public String topic;
	// Comment below not working.
	@KafkaListener(topics= "test1" , groupId ="group_id")
	public void Hello(EmployeePojo employeeRef,@Payload Object message) throws InterruptedException {
		//System.out.println("EmployeeConsumer Start--------");
		
		Thread.sleep(500);
		
		ConsumerRecord<String, Object> c = (ConsumerRecord)message;
		System.out.println( "------->  EmployeeConsumer-----> : "+ c.offset());
		//System.out.println(employeeRef);
		// System.out.println("EmployeeConsumer End-----------");
	}
	
	
}
