package com.skh;

import org.springframework.stereotype.Component;

@Component
public class PojoConfig {	

	    private String bootStrap;
	    private String group;
	    private String topic = "test";

	    public String getBootStrap() {
	        return bootStrap;
	    }

	    public void setBootStrap(String bootStrap) {
	        this.bootStrap = bootStrap;
	    }

	    public String getGroup() {
	        return group;
	    }

	    public void setGroup(String group) {
	        this.group = group;
	    }

	    public String getTopic() {
	        return topic;
	    }

	    public void setTopic(String topic) {
	        this.topic = topic;

	    }

	    @Override
	    public String toString() {
	        return "KafkaConsumerProperties [bootStrap=" + bootStrap + ", group=" + group + ", topic=" + topic + "]";
	    }
	    
}