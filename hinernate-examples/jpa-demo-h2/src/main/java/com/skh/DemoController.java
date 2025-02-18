package com.skh;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skh.O2M.Address;
import com.skh.O2M.Employee;

@RestController
public class DemoController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/") 
	public void saveEmployee() {
		
		Employee employee = new Employee(1, "emp1", new Date(), 12d, true,null);
		
		Address add1 = new Address("12", "brdndvn1", "5A1", "Hyd1", "5023071", employee);
		
		List<Address> addressList = Arrays.asList(add1);
		
		employee.setAddressList(addressList);
				
			 
		
		employeeRepository.save(employee);
		
	}

}
