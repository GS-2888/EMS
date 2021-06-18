package com.ems.command;

import com.ems.domain.model.Employee;

@FunctionalInterface
public interface RepositoryOperation {
	String execute(Employee employee);
}
