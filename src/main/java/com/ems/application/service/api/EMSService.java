package com.ems.application.service.api;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.ems.domain.model.Employee;
import com.ems.domain.model.SalaryResponse;

import io.opentracing.Span;
import net.sf.jasperreports.engine.JRException;



public interface EMSService {

public void save(Employee employee, Span rootSpan);
	
	public List<Employee> getAllEmployees(Span rootSpan);

	public Employee findEmployeeById(int employeeId);

	public void deleteById(int employeeId);

	public List<Employee> getPagedEmployees(String pageNumber, String pageSize);

	public List<Employee> getSortedEmployee(String sortString);
	
	public void updateEmailById(String email,int Id);
	
	public ByteArrayOutputStream getPayslip(Employee employee,SalaryResponse salary) throws JRException;
}
