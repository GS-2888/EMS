package com.ems.users.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.ems.users.entity.User;
import com.ems.users.request.model.UserRequest;

public interface UsersService extends UserDetailsService{

	public UserRequest createUser(UserRequest userDetails); 
	
	public User findUserByUsername(String username); 
	
}
