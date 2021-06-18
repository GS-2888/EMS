package com.ems.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.users.entity.User;

public interface UsersRepository extends JpaRepository<User,Long>{

	 public User findByEmail(String email);
}
