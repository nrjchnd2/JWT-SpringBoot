package com.neeraj.springJWTsecurity.JWTSecurity.model;

import org.springframework.stereotype.Component;

@Component
public class JwtUser {

	private Long id;
	private String userName;
	private String roles;
	public JwtUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
