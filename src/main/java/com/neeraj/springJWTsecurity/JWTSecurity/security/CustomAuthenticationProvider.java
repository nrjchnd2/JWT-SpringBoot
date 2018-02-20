package com.neeraj.springJWTsecurity.JWTSecurity.security;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.neeraj.springJWTsecurity.JWTSecurity.model.JWTAuthenticationToken;
import com.neeraj.springJWTsecurity.JWTSecurity.model.JwtUser;
import com.neeraj.springJWTsecurity.JWTSecurity.model.JwtUserDetails;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator jwtValidator;
	@Autowired
	private JwtUserDetails jwtUserDetails;
	@Override
	public boolean supports(Class<?> arg0) {
		
		return JWTAuthenticationToken.class.isAssignableFrom(arg0);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		JWTAuthenticationToken jwtToken=(JWTAuthenticationToken)arg1;
		String token=jwtToken.getToken();
		JwtUser jwtUser =(JwtUser) jwtValidator.validate(token);
		if(jwtUser==null) {
			throw new RuntimeException("Invalid  token");
		}
		List<GrantedAuthority> grantedAuthority=AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRoles());
		jwtUserDetails.setId(jwtUser.getId());
		jwtUserDetails.setUserName(jwtUser.getUserName());
		jwtUserDetails.setToken(token);
		jwtUserDetails.setGrantedAuthority(grantedAuthority);
		return jwtUserDetails;
	}

}
