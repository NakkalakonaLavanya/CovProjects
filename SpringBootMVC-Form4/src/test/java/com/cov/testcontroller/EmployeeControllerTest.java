package com.cov.testcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cov.beans.Department;
import com.cov.beans.Employee;
import com.cov.controller.EmployeeController;
import com.cov.repository.EmployeeRepository;
import com.cov.service.DepartmentService;
import com.cov.service.EmployeeService;

@Controller
class EmployeeControllerTest {
	
	@Autowired
	EmployeeController employeeController;
	@InjectMocks
	EmployeeService employeeService;
	
    
    Department d1= new Department(1, "sales");
    Department d2= new Department(2, "integrations");
    Department d3= new Department(3, "testing");
    Department d4= new Department(4, "production");
    Employee employee1=new Employee(1,"lavanya",d1);
    Employee employee2=new Employee(1,"lavanya",d1);
    Employee employee3=new Employee(1,"lavanya",d1);
    Employee employee4=new Employee(1,"lavanya",d1);
    @Test
    public void findAll() throws Exception {
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3));
        
        Mockito.when(EmployeeRepository.findAll()).thenReturn(employees);
        
        perform(MockMvcRequestBuilders
                .get("/patient")
                .contentType(MediaType.class)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }
   
	   
	   
}
