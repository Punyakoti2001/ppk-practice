package com.zettamine.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zettamine.demo.Entity.Employee;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeRepositoryTest 
{
	@Autowired
	private EmployeeRepository empRepository;

	private List<Employee> listEmployee = new ArrayList<>();

	
	
	@BeforeEach
	void setUp() throws Exception 
	{
		Employee employee1  = Employee.builder()
			       .empName("ppkReddy")
			       .phNo("8688661027")
			       .email("punya@gmail.com")
			       .deptNo(10).build();
		Employee employee2 = Employee.builder()
			       .empName("Reddy")
			       .phNo("8688661027")
			       .email("puli@gmail.com")
			       .deptNo(10).build();
		
			listEmployee.add(employee1);
			listEmployee.add(employee2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindAll() 
	{
		empRepository.saveAll(listEmployee);
		
		List<Employee> all = empRepository.findAll();
		assertThat(all.size()>0).isEqualTo(true);
		
	}
	
	@Test
	void testFindAllNull() 
	{		
		List<Employee> all = empRepository.findAll();
	
		assertThat(all.size()<=0).isEqualTo(true);
		
	}

	@Test
	void testSave()
	{
		Employee emp = Employee.builder()
			       .empName("ppkReddy")
			       .phNo("8688661027")
			       .email("punya@gmail.com")
			       .deptNo(10).build();
		
		Employee save = empRepository.save(emp);
		assertThat(save
				.getEmpId()).isGreaterThan(0);
	}
	

	@Test
	void testFindById() 
	{
		Employee emp = Employee.builder()
			       .empName("ppkReddy")
			       .phNo("8688661027")
			       .email("punya@gmail.com")
			       .deptNo(10).build();
		Employee save = empRepository.save(emp);
		
		System.err.println(save);
		
		 Employee employee = empRepository.findById(save.getEmpId()).get();
		 
		 assertThat(employee.getEmpId()).isEqualTo(save.getEmpId());
		
	}

}
