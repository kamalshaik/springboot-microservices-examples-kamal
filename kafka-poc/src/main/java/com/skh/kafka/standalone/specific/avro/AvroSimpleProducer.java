package com.skh.kafka.standalone.specific.avro;

import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

import com.skh.kafka.standalone.avro.model.Customer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

//Create java class named “SimpleProducer”
public class AvroSimpleProducer {
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	/*
	 * First run below two commads from kafka software anf then run this program.
	 * 
	 * bin\windows\zookeeper-server-start.bat config\zookeeper.properties
	 * bin\windows\kafka-server-start.bat config\server.properties
	 * 
	 * 
	 * Access this URL;
	 * https://raw.githubusercontent.com/confluentinc/cp-all-in-one/7.8.0-post/cp-
	 * all-in-one-kraft/docker-compose.yml
	 * 
	 * 1. copy all content and create as "docker-compose.yml" 2. create CMD at this
	 * file location. 3. run "docker-compose up -d" on CMD console. 4. Access below
	 * URL's. http://localhost:8081/schemas http://localhost:9021/
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) throws Exception {

		String TOPIC_NAME = "customerT22";

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
		props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
		props.put(ProducerConfig.RETRIES_CONFIG, "2");
		props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "400");
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
		props.put(ProducerConfig.LINGER_MS_CONFIG, "500");
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "200");
		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
		props.put("schema.registry.url", "http://localhost:8081");

		Producer<String, Customer> producer = new KafkaProducer<String, Customer>(props);

		Customer customer = new Customer();
		customer.setAge(12);
		customer.setAutomatedEmail(true);
		customer.setFirstName("venky");
		customer.setLastName("AAA");
		customer.setHeight(180f);
		customer.setWeight(100);
		customer.setMiddleName("middleName");
		customer.setTest("test"); 
		

		/*
		 * for (int i = 0; i < 5; i++) {
		 
			String key = null;
			if (i % 2 == 0) {
				key = customer.getFirstName().toString()+"-" + i;
			} else {
				key = customer.getFirstName().toString();
			}

			producer.send(new ProducerRecord<String, Customer>(TOPIC_NAME, key, customer));
		}
		*/
		String key = customer.getFirstName()+"-"+customer.getFirstName().length();
		producer.send(new ProducerRecord<String, Customer>(TOPIC_NAME, key, customer));
		System.out.println(ANSI_GREEN +" ****** Message sent successfully ****** " + ANSI_RESET);
		producer.close();
	}
}
