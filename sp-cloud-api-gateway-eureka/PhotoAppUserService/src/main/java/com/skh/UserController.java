package com.skh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("users")
@Tag(name = "USER-APP", description = "the USER-APP Api")
public class UserController {

	@Autowired
	private Environment env;

	@GetMapping("/display")
	@Operation(summary = "Fetch port number of MGT-APP", 
			description = "Calls MGT application and fetches port number of MGT-APP")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })

	public String display() {

		System.out.println("Inside --> UserController --> display() method. ");

		return "From --> UserController --> display() method., PORT : " + env.getProperty("server.port");
	}

	@GetMapping("/")
	@Operation(summary = "Returns some dummy test", 
		description = "fetches some dummy test from USER-APP")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })

	public String test() {
		return "From user controller default endpoint";
	}

}
