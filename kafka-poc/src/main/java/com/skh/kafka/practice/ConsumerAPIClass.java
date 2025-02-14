package com.skh.kafka.practice;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerAPIClass {

	public static void main(String[] args) throws InterruptedException {
		
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		
		properties.put("group.id", "my-group");
		
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
//		properties.put("value.deserializer", "com.skh.kafka.practice.CustomerDeserializer");
		
		
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		//properties.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "c1");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.setProperty("auto.commit.interval.ms", "1000");
		

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		String topic = "test";

		consumer.subscribe(Collections.singletonList(topic));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			
			for (ConsumerRecord<String, String> record : records) {
				// DB code
				//Thread.sleep(500);
				System.out.printf("ConsumerAPIClass==================> Received message: offset=%s, key=%s, value=%s%n", record.offset(),record.key(), record.value());
			}
		}

	}

}
