package com.ems.payroll.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.payroll.entity.Salary;
import com.ems.payroll.model.response.SalaryResponse;
import com.ems.payroll.service.SalaryService;
import com.ems.payroll.utils.SalaryUtils;

@RestController
@RequestMapping("/api")
public class SalaryRestController {
	
	private SalaryService salaryService;
	
	@Autowired
	public SalaryRestController(SalaryService salaryService) {
		this.salaryService = salaryService;
	}
	
	@GetMapping("/salarys")
	public ResponseEntity<List<Salary>> getAllEmployees() {

		return new ResponseEntity<List<Salary>>(salaryService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/salary/{empId}")
	public ResponseEntity<SalaryResponse> calculateSalary(@PathVariable(value="empId",required=true) int employeeId) {
		
		Salary salary = salaryService.findSalaryById(employeeId);
		
		SalaryResponse salaryDetails =  SalaryUtils.calculateSalary(salary);
				
		return new ResponseEntity<SalaryResponse>(salaryDetails,HttpStatus.ACCEPTED); 
	}
}
