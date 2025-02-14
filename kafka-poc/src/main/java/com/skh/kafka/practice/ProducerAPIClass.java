package com.skh.kafka.practice;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;



public class ProducerAPIClass {
	
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		// Kafka Broker / Server.
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		
		properties.put("batch.size", 33554431);	
		properties.put("linger.ms", 10000);	
		
		properties.put("compression.type", "gzip");	
		
		
		
		
		
		
		
//		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "c1");
		
		
		/*
		 * Producer, KafkaProducer --> Main classes to represent Producer.
		 * 
		 * ProducerRecord --> It represents each message/record.
		 * 
		 * Callback --> it is acknowedgement class.
		 * 
		 * RecordMetadata -->It holds all details about partition, topic, offset etc details...of the message stored.
		 * 
		 */
		
		

		Producer<String, String> producer = new KafkaProducer<>(properties);

		String topic = "test";
		String key = "key1";
		String value = "Hello, Kafka!";

		ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

		for (int i = 0; i < 5; i++) {
			producer.send(record, new Callback() {
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if (exception == null) {
						//System.out.println("Message sent successfully. partition: " + metadata.partition());
						System.out.println("Message sent successfully. Offset:    " + metadata.offset());
					} else {
						System.err.println("Error sending message: " + exception.getMessage());
					}
				}
			});

			
		}
		
		producer.close();
		
		
		
		/*
		 *  
		  
		producer.send(record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (exception == null) {
					System.out.println("Message sent successfully. partition: " + metadata.partition());
					System.out.println("Message sent successfully. Offset: " + metadata.offset());
				} else {
					System.err.println("Error sending message: " + exception.getMessage());
				}
			}
		});

		producer.close();
		*/
	}

}

/**
 * 1. Start Zookeeper
 * 2. Start broker
 * 3. Create topic.
 * 4. Start consumer java program.
 * 4. Start Producer java program to send messages.
 * 
 * 
 * 
 * 
 * 
 */



