package com.ems.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ems.users.entity.User;
import com.ems.users.request.model.LoginRequest;
import com.ems.users.service.UsersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter  extends UsernamePasswordAuthenticationFilter{

	private UsersService service; 
	
	public AuthenticationFilter(UsersService service) {
	this .service  = service;	
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		Authentication authentication = null;
		try {
			LoginRequest loginDetails = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			
			authentication =  getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getEmail(),loginDetails.getPassword(),new ArrayList()));
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
		User user = service.findUserByUsername(userName);
		
		String token = Jwts.builder()
				.setSubject(user.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("864000000")))
				.signWith(SignatureAlgorithm.HS512,"sdf5sfFDS523fdasdsdfds")
				.compact();
		
		response.addHeader("token", token);
		response.addHeader("userId", user.getUserId());
				
	}
	
}
