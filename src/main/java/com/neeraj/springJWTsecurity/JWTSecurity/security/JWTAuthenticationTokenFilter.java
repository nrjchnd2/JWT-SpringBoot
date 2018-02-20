package com.neeraj.springJWTsecurity.JWTSecurity.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.neeraj.springJWTsecurity.JWTSecurity.model.JWTAuthenticationToken;


public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{
	
	@Autowired
	private JWTAuthenticationToken jwtAuthenticationToken;

	public JWTAuthenticationTokenFilter() {
		super("/rest/**");
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest arg0, HttpServletResponse arg1)
			throws AuthenticationException, IOException, ServletException {
		String header=arg0.getHeader("Authorization");
		if(header==null ||!header.startsWith("Token ")) {
			throw new RuntimeException("JWT token is missing");
		}
		String authenticationToken=header.substring(6);
		jwtAuthenticationToken.setToken(authenticationToken);
		
		return getAuthenticationManager().authenticate(jwtAuthenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

	
	


}
