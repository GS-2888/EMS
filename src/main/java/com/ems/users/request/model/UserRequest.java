package com.ems.users.request.model;

public class UserRequest {

	
	private String email;
	
	private String password;
	
	private String userId;
	
	
	public UserRequest() {
		
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
