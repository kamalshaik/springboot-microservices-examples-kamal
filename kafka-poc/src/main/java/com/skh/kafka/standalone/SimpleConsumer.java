package com.skh.kafka.standalone;

import java.util.Properties;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SimpleConsumer {
	/*
	 * First run below two commads from kafka software anf then run this program.
	 * 
	 * bin\windows\zookeeper-server-start.bat config\zookeeper.properties
	 * bin\windows\kafka-server-start.bat config\server.properties
	 * 
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * if(args.length == 0){ System.out.println("Enter topic name"); return; }
		 */
		// Kafka consumer configuration settings
//      String topicName = args[0].toString();
		String topicName = "test";
		Properties props = new Properties();

		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		
		props.put("auto.offset.reset", "earliest"); 
		/**
		 * "earliest" --> from-begining
		 * 
		 */
		
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(topicName));

		// print the topic name
		System.out.println("Subscribed to topic " + topicName);

		IntStream.range(1, 5).forEach(index -> {
			ConsumerRecords<String, String> records = consumer.poll(10000);
			Set<TopicPartition> partitions = records.partitions();
			for (TopicPartition partition: partitions) {
				System.out.println("------------partition : ---------------"+partition.partition());
				System.out.println("------------partition : ---------------"+partition.topic());
				break;
			}
			
			 if (records.count() == 0) {
	                System.out.println("None found");
	            } else {
	            	
	            	records.forEach(record -> {
	            		System.out.println("value ------> : " + record.value());
	        			// print the offset,key and value for the consumer records.
	        			System.out.printf("offset = %d, key = %s, value = %s\n", 
	        					record.offset(),
	        			record.key(), record.value());
	            		
	            	});
	            }
			
			
		});
		
		
		
		
		
		/*while (true) {
			ConsumerRecords<String, String> records = consumer.poll(10000);
			for (ConsumerRecord<String, String> record : records)
				System.out.println("value ------> : " + record.value());
			// print the offset,key and value for the consumer records.
			// System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(),
			// record.key(), record.value());
		}


*/
	}
}

















