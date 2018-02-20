package com.neeraj.springJWTsecurity.JWTSecurity.security;

import org.springframework.stereotype.Component;

import com.neeraj.springJWTsecurity.JWTSecurity.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGenerator {
	

	public TokenGenerator() {
		super();
	}

	public String generate(JwtUser user) {
		Claims claims=Jwts.claims().setSubject(user.getUserName());
		claims.put("userId",String.valueOf(user.getId()));
		claims.put("role", user.getRoles());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512,"youtube").compact();
		
	}

}
