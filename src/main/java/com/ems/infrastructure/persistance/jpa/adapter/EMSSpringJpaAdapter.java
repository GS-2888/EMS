package com.ems.infrastructure.persistance.jpa.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ems.domain.model.Employee;
import com.ems.domain.spi.EMSPersistancePort;
import com.ems.infrastructure.persistance.jpa.entity.EmployeeEntity;
import com.ems.infrastructure.persistance.jpa.repository.EmployeeRespository;


public class EMSSpringJpaAdapter implements EMSPersistancePort{

	
	private EmployeeRespository employeeRepository;
	
	
	private ModelMapper mapper;
	
	
	public EMSSpringJpaAdapter(EmployeeRespository employeeRepository,ModelMapper mapper) {
		this.employeeRepository = employeeRepository;
		this.mapper = mapper;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		
		List<EmployeeEntity> entityList = employeeRepository.findAll();
		List<Employee> employeeList = new ArrayList<Employee>();
		for(EmployeeEntity entity: entityList) {			
			Employee employeeModel = mapper.map(entity,Employee.class); 
			employeeList.add(employeeModel);
		}	
		
		return employeeList;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub		
		EmployeeEntity entity = mapper.map(employee,EmployeeEntity.class);	
	    entity  =  employeeRepository.save(entity);
	    employee = mapper.map(entity,Employee.class);
		return employee; 
	}

	@Override
	public int deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		 employeeRepository.deleteById(employeeId);
		 return employeeId;
	}

	@Override
	public Employee updateEmplyee(Employee employee) {
		EmployeeEntity entity = mapper.map(employee,EmployeeEntity.class);	
	    entity  =  employeeRepository.save(entity);
	    employee = mapper.map(entity,Employee.class);
		return employee;
	}

	@Override
	public List<Employee> getPagedEmployees(int pageNumber, int sizeLimit) {
		// TODO Auto-generated method stub
		Pageable pagable = PageRequest.of(pageNumber, sizeLimit);
		Page<EmployeeEntity> page = employeeRepository.findAll(pagable);
		List<EmployeeEntity> entityList = page.getContent();
		List<Employee> employeeList = new ArrayList<Employee>();
		for(EmployeeEntity entity: entityList) {			
			Employee employeeModel = mapper.map(entity,Employee.class); 
			employeeList.add(employeeModel);
		}
		return employeeList;
	}

	@Override
	public List<Employee> getSortedEmployees(String sortString) {
		// TODO Auto-generated method stub
		List<EmployeeEntity> entityList  = employeeRepository.findAll(Sort.by(sortString));
		List<Employee> employeeList = new ArrayList<Employee>();
		for(EmployeeEntity entity: entityList) {			
			Employee employeeModel = mapper.map(entity,Employee.class); 
			employeeList.add(employeeModel);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		Optional<EmployeeEntity> optional  =  employeeRepository.findById(employeeId);
		EmployeeEntity entity = null;
		Employee employee = null;
		if(optional.isPresent()) {
		entity = optional.get();	
		employee = mapper.map(entity,Employee.class);
		}
		return employee;
	}

	@Override
	@Transactional
	public void updateEmployeeSetEmailForId(String email, int id) {
		// TODO Auto-generated method stub
		employeeRepository.updateEmployeeSetEmailForId(email, id);
	}

}
