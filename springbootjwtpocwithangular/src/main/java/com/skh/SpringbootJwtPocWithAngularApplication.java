package com.skh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// https://www.javainuse.com/spring/boot-jwt
//https://www.javainuse.com/spring/boot-jwt-mysql
@SpringBootApplication
public class SpringbootJwtPocWithAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtPocWithAngularApplication.class, args);
	}

}
/**
 * While sending request from Postman use below data.
 * 1. Sending authenticate request for getting token. 
    http://localhost:3000/authenticate
	Headers:
	key: [{"key":"Content-Type","value":"application/json","description":""}]
	Body : 
	{"username":"javainuse","password":"password"}
	
	2. Sending request to controller for getting data.
	http://localhost:3000/users
	[{"key":"Authorization","value":"Bearer <token generated in above process>":""}]
	
	
	3. Connecting from angular.
	Clone the Angular application from bitbucket.
	git clone https://kamalshaik@bitbucket.org/kamalshaik/angularjjwtpocwithspringboot.git
	after cloning the above project run and connect SB project, both projects should run.
	
	
 */

