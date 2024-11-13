package com.zettamine.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zettamine.demo.Entity.Employee;
import com.zettamine.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService 
{

	private EmployeeRepository empRepository;
	
	@Override
	public Employee fetchEmployee(Integer empId) {
		
		Optional<Employee> byId = empRepository.findById(empId);
		
		if(byId.isPresent())
		{
			System.err.println("hello");
			return byId.get();			
		}
		return null;
		
	}

	@Override
	public List<Employee> fetchAllEmployee() {
		List<Employee> all = empRepository.findAll();
		if(all.size()==0)
		{
			return all;
		}
		return all;
	}

	@Override
	public Boolean createEmployee(Employee emp) {
		Employee save = empRepository.save(emp);
		
		return true;
	}

	@Override
	public Employee update(Employee emp, Integer empId) {
		
		Optional<Employee> byId = empRepository.findById(empId);
		if(byId.isPresent())
		{
			Employee save = empRepository.save(emp);
			return save;
		}
		return null;
	}
	
	public int add(int a,int b)
	{
		return a+b ;
	}
	
	private int sum(int a,int b)
	{
		return a+b;
	}
	
	public static Boolean checkId(Integer id)
	{
		if(id>=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public List<Integer> streamsPro(List<Integer> list)

	{
		return list.stream().filter(e->e%2==0).collect(Collectors.toList());
	}

}
