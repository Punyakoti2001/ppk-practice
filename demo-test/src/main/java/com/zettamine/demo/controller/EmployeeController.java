package com.zettamine.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.demo.Entity.Employee;
import com.zettamine.demo.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController 
{

	private EmployeeService empService;
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee emp)
	{
		System.err.println(emp);
		Boolean res = empService.createEmployee(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	@GetMapping(path = {"/fetch/{empId}","/fetch"})
	public ResponseEntity<?> getEmployee(@PathVariable(required = false) Integer empId)
	{
		
		if(empId!= null)
		{
			Employee fetchEmployee = empService.fetchEmployee(empId);
			return ResponseEntity.status(HttpStatus.OK).body(fetchEmployee);
		}
		else {
			List<Employee> fetchEmployees = empService.fetchAllEmployee();
			return ResponseEntity.status(HttpStatus.OK).body(fetchEmployees);
		}
		
		
	}
	
	@PutMapping("/update/{empId}")
	public ResponseEntity<?>updateEmployee(@RequestBody Employee emp,@PathVariable Integer empId)
	{
	Employee updateEmp =empService.update(emp,empId);
	
	return ResponseEntity.status(HttpStatus.OK).body(updateEmp);
	}
	
	
	
}

