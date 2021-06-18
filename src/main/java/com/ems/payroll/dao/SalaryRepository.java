package com.ems.payroll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.payroll.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}
