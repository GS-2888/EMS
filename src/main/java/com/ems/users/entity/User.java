package com.ems.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="encrypted_password")
	private String encryptedPassword;
	
	

	
	public User() {
		
	}
	
	
	public User(int id,String email,String userId,String encryptedPassword) {
		this.id = id;
		this.email = email;
		this.userId = userId;
		this.encryptedPassword = encryptedPassword;
	}

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
}
