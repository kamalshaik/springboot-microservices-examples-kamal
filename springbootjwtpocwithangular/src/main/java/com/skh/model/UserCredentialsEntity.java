package com.skh.model;

import javax.persistence.*;

@Entity(name = "USER_CREDENTIALS")
public class UserCredentialsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String username;
	
	@Column
	private String password;

	@Column
	private String role;

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
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

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserCredentialsEntity [id=" + id + ", username=" + username + ", password=" + password + ", role="
				+ role + "]";
	}

}