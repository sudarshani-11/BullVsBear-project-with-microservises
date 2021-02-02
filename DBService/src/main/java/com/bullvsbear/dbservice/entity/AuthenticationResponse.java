package com.bullvsbear.dbservice.entity;

public class AuthenticationResponse {
	
	private String email;
	private String password;
	
	public AuthenticationResponse(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public AuthenticationResponse() {}

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
