package com.ems.domain.model;



import io.swagger.annotations.ApiModelProperty;

public class EmployeeRequest {

	@ApiModelProperty(notes="Company ID of employee")
	
	private int id;
	
	@ApiModelProperty(notes="Employee first name")

	private String firstName;
	
	@ApiModelProperty(notes="Employee last name")
	
	private String lastName;
	
	@ApiModelProperty(notes="Employee email id")

	private String email;
	
	@ApiModelProperty(notes="Employee grade")
	
	private String grade;
	
	@ApiModelProperty(notes="Employee pan")
	
	private String pan;
	
	
	public EmployeeRequest() {
		
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
