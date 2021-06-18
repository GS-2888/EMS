package com.ems.users.rest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.users.request.model.LoginRequest;
import com.ems.users.request.model.UserRequest;
import com.ems.users.response.model.UserResponse;
import com.ems.users.service.UsersService;

@RestController
@RequestMapping("/api")
public class UsersRestControlller {

	@Autowired
	private UsersService service;
	
	@PostMapping(path="/signup",consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest  user){
	
		user = service.createUser(user);
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponse response = mapper.map(user,UserResponse.class); 
		return new ResponseEntity<UserResponse>(response,HttpStatus.CREATED);
	}
	
	
}
