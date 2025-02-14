package com.skh.kafka.practice;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDeserializer implements Deserializer<String> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration, if needed
    }

    @Override
    public String deserialize(String topic, byte[] data) {
        // Deserialization logic
    	System.out.println("data: ");
    	System.out.println(data);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
    		String object = objectMapper.readValue(data, String.class);
			System.out.println(object);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    	
    	return new String();
    }

    @Override
    public void close() {
        // Clean-up logic, if needed
    }
}
