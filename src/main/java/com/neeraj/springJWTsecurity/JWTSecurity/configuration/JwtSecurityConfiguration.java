package com.neeraj.springJWTsecurity.JWTSecurity.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.neeraj.springJWTsecurity.JWTSecurity.security.CustomAuthenticationProvider;
import com.neeraj.springJWTsecurity.JWTSecurity.security.JWTAuthenticationEntryPoint;
import com.neeraj.springJWTsecurity.JWTSecurity.security.JWTAuthenticationTokenFilter;
import com.neeraj.springJWTsecurity.JWTSecurity.security.JWTSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired
	private JWTAuthenticationEntryPoint entryPoint;
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(customAuthenticationProvider));
	}
	@Bean
	public JWTAuthenticationTokenFilter authenticationFilter() {
		 JWTAuthenticationTokenFilter filter=new JWTAuthenticationTokenFilter();
		 filter.setAuthenticationManager(authenticationManager());
		 filter.setAuthenticationSuccessHandler(new JWTSuccessHandler());
		 return filter;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests().antMatchers("**/rest/").authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
			
	}
	
	
	

}
