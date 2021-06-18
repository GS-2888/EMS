package com.ems.payroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.payroll.dao.SalaryRepository;
import com.ems.payroll.entity.Salary;

@Service
public class SalaryServiceImpl implements SalaryService {
	
	private SalaryRepository salaryRepository;
	
	@Autowired
	public SalaryServiceImpl(SalaryRepository salaryRepository) {
		this.salaryRepository = salaryRepository;
	}

	@Override
	public Salary findSalaryById(int Id) {
		// TODO Auto-generated method stub
		Optional<Salary> optional =  salaryRepository.findById(Id);
		Salary salary = null;
		if(optional.isPresent())
			salary = optional.get();
		
		return salary;
		
	}

	@Override
	public List<Salary> getAllEmployees() {
		// TODO Auto-generated method stub
		return salaryRepository.findAll();
	}

}
