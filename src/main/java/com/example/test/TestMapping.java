package com.example.test;

import org.modelmapper.ModelMapper;

import com.ems.users.entity.User;

public class TestMapping {

	public static void main(String [] args) {
		User user = new User();
		user.setEmail("email");
		user.setEncryptedPassword("wefewf");
		user.setId(230);
		user.setUserId("sdfs");
		Bank bank = new Bank();
		bank.setBankName("bank");
		bank.setDate("date");
		ModelMapper modelMapper = new ModelMapper();
		Employee employee = modelMapper.map(user, Employee.class);
		employee = modelMapper.map(bank, Employee.class);
		System.out.print(employee);
	}
	
}
