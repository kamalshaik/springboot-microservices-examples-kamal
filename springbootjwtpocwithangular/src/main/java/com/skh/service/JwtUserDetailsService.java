package com.skh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skh.model.JwtRequest;
import com.skh.model.UserCredentialsEntity;
import com.skh.model.UserCredentialsRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialsRepository repository;
	private List<UserCredentialsEntity> credentialsEntityList;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("JwtUserDetailsService--->loadUserByUsername()");
		credentialsEntityList = repository.findByUsername(username);
		UserCredentialsEntity user = credentialsEntityList.stream()
	    .findFirst()
	    .orElse(null);
		if (null != user && username.equals(user.getUsername())) {
			//testing or checking password encrypted..
			this.saveTesting(user.getUsername(), user.getPassword());
			
			return new User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	
	public UserCredentialsEntity save(JwtRequest user) {
		UserCredentialsEntity model = new UserCredentialsEntity();
//		model.setId("200");
		model.setUsername(user.getUsername());
		model.setPassword(bcryptEncoder.encode(user.getPassword()));
		model.setRole("ADMIN");
		return repository.save(model);
	}
	public void saveTesting(String userName, String pwd) {
		System.out.println(userName +" ---> "+bcryptEncoder.encode(pwd));
	}
	
	

}