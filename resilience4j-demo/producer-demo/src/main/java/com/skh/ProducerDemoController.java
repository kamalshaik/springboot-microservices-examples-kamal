package com.skh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerDemoController {

	@GetMapping()
	public Activity getActivity() {
		boolean flag = true;
		if(flag) {
    		throw new NullPointerException();
    	}
		
		System.out.println("In ProducerDemoController -> getActivity()");
		return new Activity("activity", "type", "link", "key", 12, 1234d, 123d);
	}
	
	
	
}
