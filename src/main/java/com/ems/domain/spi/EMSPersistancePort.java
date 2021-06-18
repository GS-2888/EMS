package com.ems.domain.spi;

import java.util.List;

import com.ems.domain.model.Employee;

public interface EMSPersistancePort {

	public List<Employee> getAllEmployees();
	
	public Employee addEmployee(Employee employee);
	
	public int deleteEmployee(int employeeId);
	
	public Employee updateEmplyee(Employee employee);
	
	public List<Employee> getPagedEmployees(int pageNumber, int sizeLimit);
	
	public List<Employee> getSortedEmployees(String sortString);
	
	public Employee getEmployee(int employeeId);

	public void updateEmployeeSetEmailForId(String email, int id);
}
