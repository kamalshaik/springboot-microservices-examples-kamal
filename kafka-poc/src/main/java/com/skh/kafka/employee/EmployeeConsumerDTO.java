package com.skh.kafka.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeConsumerDTO {
	private Integer empId;
	private String empName;
	private Double empSalary;
	private Date empDOJ;
	
	
	public EmployeeConsumerDTO() {
	}

	public EmployeeConsumerDTO(Integer empId, String empName, Double empSalary, Date empDOJ) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.empDOJ = empDOJ;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public Date getEmpDOJ() {
		return empDOJ;
	}

	public void setEmpDOJ(Date empDOJ) {
		this.empDOJ = empDOJ;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", empDOJ=" + empDOJ
				+ "]";
	}
	
	public static List<EmployeeConsumerDTO> getEmployees(){
		List<EmployeeConsumerDTO> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(new EmployeeConsumerDTO(i, "empName - "+i, (double)i+1000, new Date()));			
		}
		return list;
	}
	

}
