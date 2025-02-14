package com.skh;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    
	@Value("#{pojoConfig.topic ?: ' '}")
	public String topic;
	
//	@KafkaListener(topics= "t1" , groupId ="group_id")
//	@KafkaListener(topics= "#{pojoConfig.topic}" , groupId ="group_id")
//	@KafkaListener(topics= "#{__listener.topic}" , groupId ="group_id")
//	@KafkaListener(topics= "${topicName}" , groupId ="group_id")
	public void Hello(Employee employeeRef,@Payload Object message) {
		System.out.println("Topic : "+topic);
		System.out.println("Message from Consumer : "+employeeRef);
		
		System.out.println(String.format("User created -> %s", message));
		
		ConsumerRecord<String, Object> c = (ConsumerRecord)message;
		System.out.println("message : "+c.value());
		
	}
}
