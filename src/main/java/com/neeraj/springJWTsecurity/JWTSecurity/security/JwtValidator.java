package com.neeraj.springJWTsecurity.JWTSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neeraj.springJWTsecurity.JWTSecurity.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class JwtValidator {

	@Autowired
	JwtUser jwtUser;
	
	
	public JwtValidator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public JwtUser validate(String token) {
		try {
		Claims claim=Jwts.parser().setSigningKey("youtube").parseClaimsJws(token).getBody();
		jwtUser.setUserName(claim.getSubject());
		jwtUser.setId(Long.parseLong((String)claim.get("userId")));
		jwtUser.setRoles((String)claim.get("role"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return jwtUser;
	}

}
