package com.clone.myntra.support.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.clone.myntra.service.UserService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Boolean isAuthenticated = userService.validateUser(userName,password );
		
//		authentication.setAuthenticated(isAuthenticated);
		
		if (isAuthenticated) {
			return new UsernamePasswordAuthenticationToken(userName,password, Arrays.asList());
		} else {
			throw new BadCredentialsException("Invalid user Name password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
