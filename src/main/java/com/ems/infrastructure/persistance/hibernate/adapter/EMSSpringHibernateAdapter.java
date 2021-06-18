package com.ems.infrastructure.persistance.hibernate.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import com.ems.domain.model.Employee;
import com.ems.domain.spi.EMSPersistancePort;
import com.ems.infrastructure.persistance.jpa.entity.EmployeeEntity;

public class EMSSpringHibernateAdapter implements EMSPersistancePort{

	private EntityManager entityManager;
	
	private ModelMapper mapper;
	
	
	
	public EMSSpringHibernateAdapter(EntityManager entityManager, ModelMapper mapper) {
		this.entityManager = entityManager;
		this.mapper = mapper;
	}
	
	@Override
	public List<Employee> getAllEmployees() {				
		Session currentSession = entityManager.unwrap(Session.class);
		Query<EmployeeEntity> query = currentSession.createQuery("from EmployeeEntity", EmployeeEntity.class);
		List<EmployeeEntity> employees = query.getResultList();
		List<Employee> employeeList = new ArrayList<Employee>();
		for(EmployeeEntity entity: employees) {			
			Employee employeeModel = mapper.map(entity,Employee.class); 
			employeeList.add(employeeModel);
		}	
		return employeeList;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Session currentSession = entityManager.unwrap(Session.class);
		EmployeeEntity entity = mapper.map(employee,EmployeeEntity.class);
		currentSession.saveOrUpdate(entity);
		return employee;
	}

	@Override
	public int deleteEmployee(int employeeId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("delete from EmployeeEntity where id=:employeeId");
		query.setParameter("employeeId", employeeId);
		query.executeUpdate();
		return employeeId;
	}

	@Override
	public Employee updateEmplyee(Employee employee) {	
		Session currentSession = entityManager.unwrap(Session.class);
		EmployeeEntity entity = mapper.map(employee,EmployeeEntity.class);
		currentSession.saveOrUpdate(entity);
		return employee;
	}

	@Override
	public List<Employee> getPagedEmployees(int pageNumber, int sizeLimit) {
		Session currentSession = entityManager.unwrap(Session.class);
		String countQ = "Select count (e.id) from EmployeeEntity e";
		Query countQuery = currentSession.createQuery(countQ);
		Long countResults = (Long) countQuery.uniqueResult();
		
		int lastPageNumber = (int) (Math.ceil(countResults / sizeLimit));
		List<EmployeeEntity> entityList = null;
		if(pageNumber < lastPageNumber) {
		
			Query selectQuery = currentSession.createQuery("From EmployeeEntity");
		    selectQuery.setFirstResult((pageNumber - 1) * sizeLimit);
		    selectQuery.setMaxResults(sizeLimit);
		    entityList = selectQuery.list();
		}
		List<Employee> employeeList = new ArrayList<Employee>();
		for(EmployeeEntity entity: entityList) {			
			Employee employeeModel = mapper.map(entity,Employee.class); 
			employeeList.add(employeeModel);
		}	
		return employeeList;	    
	}

	@Override
	public List<Employee> getSortedEmployees(String sortString) {
		Session currentSession = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<EmployeeEntity> cr = cb.createQuery(EmployeeEntity.class);
		Root<EmployeeEntity> root = cr.from(EmployeeEntity.class);
		cr.select(root);		
		cr.orderBy(cb.asc(root.get(sortString)));
		Query<EmployeeEntity> query = currentSession.createQuery(cr);
		List<EmployeeEntity> results = query.getResultList();
		List<Employee> employeeList = new ArrayList<Employee>();
		for(EmployeeEntity entity: results) {			
			Employee employeeModel = mapper.map(entity,Employee.class); 
			employeeList.add(employeeModel);
		}	
		return employeeList;
	}

	@Override
	public Employee getEmployee(int employeeId) {				
		Session currentSession = entityManager.unwrap(Session.class);
		Employee employee =	currentSession.get(Employee.class, employeeId);
		return employee;
	}

	@Override
	public void updateEmployeeSetEmailForId(String email, int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("update User set name=:email where id=:id");
		query.setParameter("email", email);
		query.setParameter("id", id);
		query.executeUpdate();		
	}

}
