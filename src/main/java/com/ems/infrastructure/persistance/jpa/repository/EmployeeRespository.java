package com.ems.infrastructure.persistance.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ems.infrastructure.persistance.jpa.entity.EmployeeEntity;





public interface EmployeeRespository extends JpaRepository<EmployeeEntity, Integer>{

	@Modifying
	@Query("update EmployeeEntity e set e.email = :email where e.id = :id")
	public void updateEmployeeSetEmailForId(@Param("email") String email, @Param("id") int id);
}
