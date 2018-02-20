package com.neeraj.springJWTsecurity.JWTSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neeraj.springJWTsecurity.JWTSecurity.model.JwtUser;
import com.neeraj.springJWTsecurity.JWTSecurity.security.TokenGenerator;

@RestController
public class TokenController {
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@PostMapping("/token")
	public String getToken(@RequestBody JwtUser user) {
		
		String token=tokenGenerator.generate(user);
		return token;
	}

}
