package com.zettamine.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zettamine.demo.Entity.Employee;
import com.zettamine.demo.service.EmployeeService;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest 
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService; 
	
	private Employee employee1;
	private Employee employee2;
	
	private List<Employee> listEmployee = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception 
	{
		employee1 = new Employee(1,"ppk","8688661027","pulicherla@gmail.com",10);
		employee2 = new Employee(2,"ppk","8688661026","pulicherlapunya@gmail.com",10);
		listEmployee.add(employee1);
		listEmployee.add(employee2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetEmployee() 
	{
		
		when(employeeService.fetchEmployee(1)).thenReturn(employee1);
		try {
			
			this.mockMvc.perform(get("/employee/fetch/1")).andDo(print())
			           .andExpect(status().isOk());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
//		mockMvc.perform(MockMvcRequestBuilders.get("/employee/fetch/1")).andDo(print());
		
	}
	
	@Test
	void testGetAllEmployee() throws Exception {
		
		when(employeeService.fetchAllEmployee()).thenReturn(listEmployee);

		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/fetch")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void testCreateEmployee() throws Exception
	{
		 ObjectMapper objMap = new ObjectMapper();

	        objMap.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

	     ObjectWriter ow = objMap.writer().withDefaultPrettyPrinter();
	     
	     String jsonRequest = ow.writeValueAsString(employee1);

		when(employeeService.createEmployee(employee1)).thenReturn(true);
		
	
		mockMvc.perform(post("/employee/create").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
		.andDo(print()).andExpect(status().isCreated());
		
	}
	
	@Test
	void testUpdate() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writerWithDefaultPrettyPrinter();
		String jsonContent = ow.writeValueAsString(employee1);
		System.err.println(jsonContent);
		when(employeeService.update(employee1,1)).thenReturn(employee1);
		
		mockMvc.perform(put("/employee/update/1").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
		                                         .andDo(print()).andExpect(status().isOk());
	}

}
