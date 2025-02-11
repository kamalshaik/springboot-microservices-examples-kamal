package com.skh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbDetailsRestController {

	@Autowired
	private DbDetailsCfgProperties dbDetailsCfgProperties;
	
	@Autowired
	private Environment env;
 

	@GetMapping("/")
	public DBDetails getDbDetails() {
		DBDetails dBDetails = new DBDetails();
		dBDetails.setDriverClassName(dbDetailsCfgProperties.getDriverClassName());
		dBDetails.setPassword(dbDetailsCfgProperties.getPassword());
		dBDetails.setUrl(dbDetailsCfgProperties.getUrl());
		dBDetails.setUserName(dbDetailsCfgProperties.getUserName());
		
		System.out.println("property from cloud enabled properties files : "+env.getProperty("myName"));

		System.out.println("property from cloud enabled properties files : "+env.getProperty("mailId"));

		
		return dBDetails;
	}

}
