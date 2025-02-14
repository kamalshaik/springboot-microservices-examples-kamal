package com.skh.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGeneratorExample {
	public static void main(String[] args) {

		int i = 0;
		while (i < 5) {
			String password = "kamal";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			//slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6
			System.out.println(hashedPassword);
			i++;
		}

	  }
}