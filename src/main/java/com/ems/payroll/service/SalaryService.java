package com.ems.payroll.service;

import java.util.List;

import com.ems.payroll.entity.Salary;

public interface SalaryService {

	public Salary findSalaryById(int id) ;

	public List<Salary> getAllEmployees();
	
}
