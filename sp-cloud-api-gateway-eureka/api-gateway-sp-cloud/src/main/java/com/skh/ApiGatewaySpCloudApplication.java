package com.skh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewaySpCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewaySpCloudApplication.class, args);
	}

}
