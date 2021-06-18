package com.ems.users.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ems.users.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private BCryptPasswordEncoder encoder;
	
	private UsersService service;
	
	@Autowired
	public WebSecurity(BCryptPasswordEncoder encoder, UsersService service) {
		this.encoder = encoder;
		this.service = service;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress("192.168.1.6")
		.and()
		.addFilter(getAuthenticationFilter());
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		// TODO Auto-generated method stub
		AuthenticationFilter filter  = new AuthenticationFilter(service);
		filter.setAuthenticationManager(authenticationManager());
		filter.setFilterProcessesUrl("/api/login");		
		return filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(service).passwordEncoder(encoder);
	}
}
