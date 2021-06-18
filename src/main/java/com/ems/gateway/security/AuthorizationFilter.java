package com.ems.gateway.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{

	
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
		
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken userToken= getAuthenticationToken(request);
		response.addHeader("Authorization", authorizationHeader);
		SecurityContextHolder.getContext().setAuthentication(userToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader == null)
		return null;
		
		String token  = authorizationHeader.replace("Bearer", "").trim();
		
		String userId = Jwts.parser()
				.setSigningKey("sdf5sfFDS523fdasdsdfds")
				.parseClaimsJws(token)
				.getBody().getSubject();
		
		if(userId==null)
			return null;
		
		return new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
	}

}
