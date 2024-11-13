package com.zettamine.demo.service;

import java.util.List;

import com.zettamine.demo.Entity.Employee;

public interface EmployeeService 
{
	Employee fetchEmployee(Integer empId);

	List<Employee> fetchAllEmployee();

	Boolean createEmployee(Employee emp);

	Employee update(Employee emp, Integer empId);

}
