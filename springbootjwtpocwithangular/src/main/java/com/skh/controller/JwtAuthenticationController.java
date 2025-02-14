package com.skh.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skh.config.JwtTokenUtil;
import com.skh.model.JwtRequest;
import com.skh.model.JwtUser;

@RestController
@CrossOrigin()
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtUser responseUser = new JwtUser(1, "Kamal_From_Server", "", "Kamal_From_Server", "Pwd_From_Server", token);
//		return ResponseEntity.ok(new JwtResponse(token));
		return ResponseEntity.ok(responseUser);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

/**
 * 
 * Create User like below.
 * http://localhost:3000/saveNewUser
 * 
 *  pass below object in body part
 * 
  {"username":"kamal","password":"kamal", "role":"ADMIN"}
 
 * select raw and application/json
 * 
 * After sending request--> get the result and save it some where.
 * 
 * 
 * result lookes like below:
 * 
  {
    "id": 4,
    "username": "kamal",
    "password": "$2a$10$4EKhNYBgsxAmxQDmYLJqTOwDDc39.4zKVcGE7DXZzHLKHCgb6nGt.",
    "role": "ADMIN"
}
 * 
 * 
 */



/**
 * 
 * After generating user name and password
 * Generate token by sending POST request to http://localhost:3000/authenticate
 * 
 * Headers: Key : Content-Type value : application/json
 * 
 * Body: {"username":"javainuse","password":"password"} it is raw type and JSOn
 * type.
 * 
 * 
 * * For other controller methods this we need to send either get / post request
 * but we need to add header Header Key : Authorization value: Bearer <token
 * Generated>
 *
 *
 *
 *
 * For angular:
 * git clone https://kamalshaik@bitbucket.org/kamalshaik/angularjjwtpocwithspringboot.git
 * get the project and run "npm install" from terminal.
 * once after starting the server .. lgon from browser..
 *
 * click on "Create New User" enter any user name and password.
 * user will be created, next login with same user name and password.
 *
 *
 *
 *
 *
 *
 *
 */
