package com.zettamine.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zettamine.demo.Entity.Employee;
import com.zettamine.demo.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest 
{
	@Mock
	private EmployeeRepository empRepository;
	
	@InjectMocks
	private EmployeeServiceImpl empService;
	
	private List<Employee> listEmployee = new ArrayList<>();
	
	private List<Employee> emptyList = new ArrayList<>();

	Employee employee1 ;
	@BeforeEach
	void setUp() throws Exception 
	{
		employee1 = new Employee(1,"ppk","8688661027","pulicherla@gmail.com",10);
	Employee employee2 = new Employee(2,"ppk","8688661026","pulicherlapunya@gmail.com",10);
		listEmployee.add(employee1);
		listEmployee.add(employee2);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		
	}

	@Test
	void testFetchEmployee() 
	{
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("ppk");
		emp.setPhNo("8688661027");
		emp.setEmail("puli@gmail.com");
		emp.setDeptNo(10);
	
		when(empRepository.findById(1)).thenReturn(Optional.of(emp));
		
		Employee fetchEmployee = empService.fetchEmployee(1);
		assertThat(fetchEmployee.getEmpName()).isEqualTo("ppk");
	}
	
	@Test
	void testFetchEmployeeNull() 
	{
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("ppk");
		emp.setPhNo("8688661027");
		emp.setEmail("puli@gmail.com");
		emp.setDeptNo(10);
		
		when(empRepository.findById(1)).thenReturn(Optional.of(emp));
		
		Employee fetchEmployee = empService.fetchEmployee(2);
		
		assertThatNullPointerException();
	}

	@Test
	void testFetchAllEmployee() 
	{
		when(empRepository.findAll()).thenReturn(listEmployee);
		assertThat(empService.fetchAllEmployee().size()!=0).isEqualTo(true);
		
	}

	
	@Test
	void testFetchAllEmployeeEmpty() 
	{
		when(empRepository.findAll()).thenReturn(emptyList);
		assertThat(empService.fetchAllEmployee().size()==0).isEqualTo(true);
	}
	@Test
	void testCreateEmployee() 
	{
		when(empRepository.save(employee1)).thenReturn(employee1);
		
		assertThat(empService.createEmployee(employee1)).isEqualTo(true);
	}

	@Test
	void testUpdate() {

//		Employee empUpdated = new Employee();
//		empUpdated.setEmpId(1);
//		empUpdated.setEmpName("ppkReddy");
//		empUpdated.setPhNo("8688661027");
//		empUpdated.setEmail("puli@gmail.com");
//		empUpdated.setDeptNo(10);
		
		Employee empUpdated = Employee.builder()
				       .empId(1)
				       .empName("ppkReddy")
				       .phNo("8688661027")
				       .email("punya@gmail.com")
				       .deptNo(10).build();
		
		when(empRepository.findById(1)).thenReturn(Optional.of(employee1));
		when(empRepository.save(empUpdated)).thenReturn(empUpdated);
		
		assertThat(empService.update(empUpdated, 1)).isEqualTo(empUpdated);
	}
	
	@Test
	void testAdd()
	{
		int i = empService.add(10,20);
		
		assertThat(i).isEqualTo(30);
	}
	
	@Test
	void testSum() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method method = EmployeeServiceImpl.class.getDeclaredMethod("sum",int.class,int.class);
		
		method.setAccessible(true);
		int sum =(int) method.invoke(empService,10,20);
		
		assertThat(sum).isEqualTo(30);
		
	}
	
	@Test
	void testCheckId()
	{
		try(MockedStatic<EmployeeServiceImpl> mocked = mockStatic(EmployeeServiceImpl.class))
				{
					mocked.when(()->EmployeeServiceImpl.checkId(10)).thenReturn(true);
					
					Boolean checkId = EmployeeServiceImpl.checkId(-10);
					assertThat(checkId).isEqualTo(false);
				}
		
	}
	@Test
	void testStream()
	{
		List<Integer> list =List.of(1,2,3,4,5,6,7);
		List<Integer> expect = List.of(2,4,6);
		List<Integer> streamsPro = empService.streamsPro(list);
		assertThat(streamsPro).isEqualTo(expect);
	}

}
