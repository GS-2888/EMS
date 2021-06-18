package com.ems.users.service;


import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.users.dao.UsersRepository;
import com.ems.users.entity.User;
import com.ems.users.request.model.UserRequest;

@Service
public class UsersServiceImpl implements UsersService {

	private UsersRepository repository;
	
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	public UsersServiceImpl(UsersRepository repository,BCryptPasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@Override
	public UserRequest createUser(UserRequest userDetails) {
		// TODO Auto-generated method stub
		
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User userEntity = mapper.map(userDetails,User.class); 
		userEntity.setEncryptedPassword(encoder.encode(userDetails.getPassword()));
		repository.save(userEntity);
		return userDetails;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repository.findByEmail(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getEncryptedPassword(),true,true,true,true,new ArrayList());
	}

	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.findByEmail(username);
	}

}
