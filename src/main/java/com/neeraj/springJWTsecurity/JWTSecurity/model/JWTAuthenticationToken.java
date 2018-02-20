package com.neeraj.springJWTsecurity.JWTSecurity.model;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = 1L;
	private String token;
	
	public JWTAuthenticationToken() {
		super(null,null);
	}

	public JWTAuthenticationToken(Object principal, Object credentials,String token) {
		super(null, null);
		this.token=token;
		
	}
	@Override
	public Object getCredentials() {
		
		return null;
	}
	@Override
	public Object getPrincipal() {
		
		return null;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
