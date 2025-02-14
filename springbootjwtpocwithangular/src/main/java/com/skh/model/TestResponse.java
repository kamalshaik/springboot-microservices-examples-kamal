package com.skh.model;

public class TestResponse {
	
	Integer id;
	String username;
	String password;
	String firstName;
	String lastName;
	
	String token;

	public TestResponse() {
	}

	public TestResponse(Integer id, String username, String password, String firstName, String lastName,String token) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}
	public String getToken() {
		return token;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtUser [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
