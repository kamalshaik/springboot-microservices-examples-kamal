package com.skh.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.skh.model.JwtRequest;
import com.skh.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.skh.model.JwtUser;
import com.skh.model.TestResponse;

@RestController
@CrossOrigin()
public class HelloWorldController {

// response always should be object only normal string not able to return.
	// need to check the issue.
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}

	@RequestMapping({ "/users" })
	public List<JwtUser> users() {
		System.out.println("HelloWorldController ---> users()");
		return Arrays.asList(new JwtUser(1, "username_from_server",
				"password_from_server", "Kamal_from_server",
				"Shaik_from_server", "token_from_server"));
	}

	@RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
	public ResponseEntity<?> saveNewUser(@RequestBody JwtRequest newUserCredentials) throws Exception {
		System.out.println("HelloWorldController ---> saveNewUser() ");
		return ResponseEntity.ok(jwtUserDetailsService.save(newUserCredentials));
	}
	
	@RequestMapping({ "/fetchServiceData" })
	public TestResponse fetchServiceData(HttpServletRequest request) {
		
		
		return new TestResponse(1, "Data_from_server",
				"Data_from_server", "Data_from_server",
				"Data_from_server", request.getHeader("Authorization"));
	}
	
	/*
	 * Below URL is always giving error, we are returning a string, but 
	 * UI side we are getting error message OK, we need to investigate further.
	 */
	@RequestMapping({ "/fetchServiceDataError" })
	public String fetchServiceDataError() {
		return "Empty String.....";
	}
	
}



/**
 * For executing this we need to send either get / post request but we need to
 * add header Header Key : Authorization value: Bearer <token Generated - check
 * below process>
 * 
 * 
 * 
 * Generate token by sending POST request to http://localhost:3000/authenticate
 * 
 * Headers: Key : Content-Type value : application/json
 * 
 * Body: {"username":"javainuse","password":"password"} it is raw type and JSOn
 * type.
 */
