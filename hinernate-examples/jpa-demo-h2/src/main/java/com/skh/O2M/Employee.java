package com.skh.O2M;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {

	@Id
	private Integer empId;
	private String empName;
	private Date empDOJ;
	private Double empSalary;
	private Boolean isPermenentEmp;
	
	@OneToMany(mappedBy = "employee_O2M",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Address> addressList;

	public Employee() {

	}

	 

	public Employee(Integer empId, String empName, Date empDOJ, Double empSalary, Boolean isPermenentEmp,
			List<Address> addressList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDOJ = empDOJ;
		this.empSalary = empSalary;
		this.isPermenentEmp = isPermenentEmp;
		this.addressList = addressList;
	}



	public List<Address> getAddressList() {
		return addressList;
	}



	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
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

	public Date getEmpDOJ() {
		return empDOJ;
	}

	public void setEmpDOJ(Date empDOJ) {
		this.empDOJ = empDOJ;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public Boolean getIsPermenentEmp() {
		return isPermenentEmp;
	}

	public void setIsPermenentEmp(Boolean isPermenentEmp) {
		this.isPermenentEmp = isPermenentEmp;
	}



	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empDOJ=" + empDOJ + ", empSalary=" + empSalary
				+ ", isPermenentEmp=" + isPermenentEmp + ", addressList=" + addressList + "]";
	}

	 

}
