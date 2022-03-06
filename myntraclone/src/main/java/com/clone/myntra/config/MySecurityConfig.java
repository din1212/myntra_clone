package com.clone.myntra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	
	//Implement this class
	@Autowired
	private AuthenticationProvider authenticateProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticateProvider);
		
	}
	
	//we can exclude few api from authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
			.antMatchers("/v1/product/signup").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
		
		//disable csrf for all post API request
		http
		.csrf()
		.disable();
	}
	
	
}
