package com.skh.O2M;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Address {
	
	@Id
	private String houseNumber;
	private String streetName;
	private String roadNumber;
	private String city;
	private String pinNumber;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_any_name")
	private Employee employee_O2M;
	
	
	public Address() {
		
	}
	 
	public Address(String houseNumber, String streetName, String roadNumber, String city, String pinNumber,
			Employee employee_O2M) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.roadNumber = roadNumber;
		this.city = city;
		this.pinNumber = pinNumber;
		this.employee_O2M = employee_O2M;
	}

	
	public Employee getEmployee_O2M() {
		return employee_O2M;
	}

	public void setEmployee_O2M(Employee employee_O2M) {
		this.employee_O2M = employee_O2M;
	}

	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getRoadNumber() {
		return roadNumber;
	}
	public void setRoadNumber(String roadNumber) {
		this.roadNumber = roadNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", streetName=" + streetName + ", roadNumber=" + roadNumber
				+ ", city=" + city + ", pinNumber=" + pinNumber + ", employee_O2M=" + employee_O2M + "]";
	}
	 
	
	

}
