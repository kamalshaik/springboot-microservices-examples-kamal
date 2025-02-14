package com.skh.kafka.practice;

import java.util.Properties;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

public class StreamAPIClass {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-streams-app");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> sourceStream = builder.stream("my_input_topic");

		KStream<String, String> transformedStream = sourceStream
				.filter((key, value) -> value.contains("important"))
				.mapValues(value -> value.toUpperCase());

		transformedStream.to("my_output_topic");

		KafkaStreams streams = new KafkaStreams(builder.build(), properties);
		streams.start();

		// Shutdown hook to gracefully stop the stream processing application
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}

}
