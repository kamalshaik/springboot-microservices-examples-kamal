package com.skh.kafka.standalone.generic.avro;

import java.util.Properties;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import com.skh.kafka.standalone.avro.model.Customer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class AvroGenericSimpleConsumer {
	/*
	 * First run below two commads from kafka software anf then run this program.
	 * 
	 * bin\windows\zookeeper-server-start.bat config\zookeeper.properties
	 * bin\windows\kafka-server-start.bat config\server.properties
	 * 
	 */
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	public static void main(String[] args) throws Exception {
		/*
		 * if(args.length == 0){ System.out.println("Enter topic name"); return; }
		 */
		// Kafka consumer configuration settings
//      String topicName = args[0].toString();
		String topicName = "customerT2";
		Properties props = new Properties();

		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");

		props.put("auto.offset.reset", "earliest");
		props.put("schema.registry.url", "http://localhost:8081");
		/**
		 * "earliest" --> from-begining
		 * 
		 */

		props.put("specific.avro.reader", "false");
		/*
		 * Here we are sending Customer object to topic, it is specific so we use above configuration.
		 * if we use GenericRecord then we can omit above property of we cna set it to false value.
		 */
		
		props.put("key.deserializer", KafkaAvroDeserializer.class.getName());
		props.put("value.deserializer", KafkaAvroDeserializer.class.getName());
		KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(props);

		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(topicName));

		// print the topic name
		System.out.println("Subscribed to topic " + topicName);

		IntStream.range(1, 5).forEach(index -> {
			ConsumerRecords<String, GenericRecord> records = consumer.poll(10000);
			for (ConsumerRecord<String, GenericRecord> record : records) {
				String key = record.key();
				GenericRecord genericRecord = record.value();

				System.out.println(genericRecord.get("emp_first_name"));
				System.out.println(genericRecord.get("emp_middle_name"));
				System.out.println(genericRecord.get("emp_age"));
				System.out.println(genericRecord.get("emp_last_name"));
				System.out.println(genericRecord.get("emp_height"));
				System.out.println(genericRecord.get("emp_weight"));
				System.out.println(genericRecord.get("emp_automated_email"));
				System.out.println(genericRecord.get("emp_test"));

			}

			Set<TopicPartition> partitions = records.partitions();
			for (TopicPartition partition : partitions) {
				System.out.println("------------partition : ---------------" + partition.partition());
				System.out.println("------------partition : ---------------" + partition.topic());
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			if (records.count() == 0) {

				System.out.println(ANSI_RED + "  ****** None found ****** " + ANSI_RESET);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				records.forEach(record -> {
					System.out.println("value ------> : " + record.value());
					// print the offset,key and value for the consumer records.
					System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(),
							record.value());

					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				});
			}

		});

		/*
		 * while (true) { ConsumerRecords<String, String> records =
		 * consumer.poll(10000); for (ConsumerRecord<String, String> record : records)
		 * System.out.println("value ------> : " + record.value()); // print the
		 * offset,key and value for the consumer records. //
		 * System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), //
		 * record.key(), record.value()); }
		 * 
		 * 
		 */
	}
}
