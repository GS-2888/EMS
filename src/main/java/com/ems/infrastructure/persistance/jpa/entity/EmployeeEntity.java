package com.ems.infrastructure.persistance.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="employee")
public class EmployeeEntity {		
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
	
		@NotEmpty(message="First name required.")
		@Column(name="first_name")
		private String firstName;
		
	
		@NotEmpty(message="Last name required.")
		@Column(name="last_name")
		private String lastName;
		
	
		@NotEmpty(message="Email required.")
		@Email
		@Column(name="email")
		private String email;
		
	
		@NotEmpty(message="Grade required.")	
		@Column(name="grade")
		private String grade;
		
	
		@NotEmpty(message="Pan required.")	
		@Column(name="pan")
		private String pan;
		
		
		public EmployeeEntity() {
			
		}
		
	
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getPan() {
			return pan;
		}

		public void setPan(String pan) {
			this.pan = pan;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	
}
