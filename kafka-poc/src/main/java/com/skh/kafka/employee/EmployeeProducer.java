
package com.skh.kafka.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.skh.Employee;

@Service
public class EmployeeProducer {

//	@Value("${topicName}") // getting value from .properties file.
	// @Value("#{pojoConfig.topic ?: ' '}") // getting value from other class field.
	private String TOPIC = "test";
	
	@Autowired
	private KafkaTemplate<String, EmployeePojo> kafkaTemplate;

	public void publishEmployee(EmployeePojo employee) {

		ListenableFuture<SendResult<String, EmployeePojo>> future = kafkaTemplate.send(TOPIC, employee);

		future.addCallback(new ListenableFutureCallback<SendResult<String, EmployeePojo>>() {

			@Override
			public void onSuccess(SendResult<String, EmployeePojo> result) {
//				System.out.println("EmployeeProduce: " + employee + "] offset=["+ result.getRecordMetadata().offset() + "]");
//				System.out.println("offset: "+ result.getRecordMetadata().offset());
				System.out.println( "--> EmployeeProducer-----> : "+ result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("EmployeeProducer onFailure()--> Unable to send message=[" + employee + "] due to : "
						+ ex.getMessage());
			}

		});
	}
	
	
	public void publishEmployeeTest(EmployeePojo employee) {

		ListenableFuture<SendResult<String, EmployeePojo>> future = kafkaTemplate.send(TOPIC, employee);
		future.addCallback(new ListenableFutureCallback<SendResult<String, EmployeePojo>>() {

			@Override
			public void onSuccess(SendResult<String, EmployeePojo> result) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				
			}
		});

		 
	}
	
	

}