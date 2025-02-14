package com.skh.kafka.standalone;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        // https://kafka.apache.org/0100/documentation.html#producerconfigs
        // create producer () -> properties
        // create producer.
        // send data.
        Properties props = new Properties();

        //Assign localhost id
        props.setProperty("bootstrap.servers","localhost:9092");
        // or
//        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");

        //Set acknowledgements for producer requests.
        props.setProperty("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.setProperty("retries", "0");

        //Specify buffer size in config
        props.setProperty("batch.size", "16384");

        //Reduce the no of requests less than 0
        props.setProperty("linger.ms", "1");

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.setProperty("buffer.memory", "33554432");

        props.setProperty("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.setProperty("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
// or
      //  props.setProperty("key.serializer", StringSerializer.class.getName());

       // props.setProperty("value.serializer", StringSerializer.class.getName());


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);


        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<String, String>("test","QWQWQW");

        producer.send(producerRecord);
        producer.flush();
        producer.close();


    }
}










