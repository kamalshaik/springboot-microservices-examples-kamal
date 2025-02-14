package com.skh.kafka.standalone.generic.avro;

import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericRecord;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

import com.skh.kafka.standalone.avro.model.Customer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

//Create java class named “SimpleProducer”
public class AvroGenericSimpleProducer {
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

		Producer<String, GenericRecord> producer = new KafkaProducer<String, GenericRecord>(props);

		Parser parser = new Schema.Parser();

		/*
		 * Copy below json content from avro folder text file and paste here.
		 */

		Schema schema = parser.parse("{\r\n" + "     \"type\": \"record\",\r\n"
				+ "     \"namespace\": \"com.skh.kafka.standalone.avro.model\",\r\n"
				+ "     \"name\": \"Employee\",\r\n" + "     \"fields\": [\r\n"
				+ "       { \"name\": \"emp_first_name\", \"type\": [\"null\", \"string\"], \"default\": null, \"doc\": \"First Name of Customer\" },\r\n"
				+ "       { \"name\": \"emp_middle_name\", \"type\": [\"null\", \"string\"], \"default\": null, \"doc\": \"Last Name of Customer\" },\r\n"
				+ "       { \"name\": \"emp_age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\" },\r\n"
				+ "	   { \"name\": \"emp_last_name\", \"type\": [\"null\", \"string\"], \"default\": null,\"doc\": \"Last Name of Customer\" },\r\n"
				+ "	   { \"name\": \"emp_test\", \"type\": [\"null\", \"string\"], \"default\": null,\"doc\": \"testField\" },\r\n"
				+ "       { \"name\": \"emp_height\", \"type\": \"float\", \"doc\": \"Height at the time of registration in cm\" },\r\n"
				+ "       { \"name\": \"emp_weight\", \"type\": \"float\", \"doc\": \"Weight at the time of registration in kg\" },\r\n"
				+ "       { \"name\": \"emp_automated_email\", \"type\": \"boolean\", \"default\": true, \"doc\": \"Field indicating if the user is enrolled in marketing emails\" }\r\n"
				+ "     ]\r\n" + "}");

//		Record record = new GenericData.Record(schema);
		GenericRecord employeeRecord = new GenericData.Record(schema);
		employeeRecord.put("emp_first_name", "kamal");
		employeeRecord.put("emp_middle_name", "emp_middle_name_value");
		employeeRecord.put("emp_age", "1");
		employeeRecord.put("emp_last_name", "emp_last_name");
		employeeRecord.put("emp_test", "emp_test_value");
		employeeRecord.put("emp_height", "123");
		employeeRecord.put("emp_weight", "123");
		employeeRecord.put("emp_automated_email", "true");

//		
//		ProducerRecord<String, GenericData> producerRecord = new ProducerRecord<String, GenericData>(TOPIC_NAME,
//				employeeRecord.get("emp_first_name").toString(), employeeRecord);
		ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<String, GenericRecord>(TOPIC_NAME,
				employeeRecord.get("emp_first_name").toString(), employeeRecord);

		producer.send(producerRecord);
		System.out.println(ANSI_GREEN + " ****** Message sent successfully ****** " + ANSI_RESET);
		producer.close();
	}
}
