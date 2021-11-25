
package com.cov.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cov.beans.Department;
import com.cov.beans.Employee;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "API to perform operations on employee",
description = "This API provides capability to perform different CRUD operations on employee repositopry")

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	static Logger logger = Logger.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;
	 @ApiOperation(value = "search a single employee based on the ID given",response = Employee.class,
	    		produces = "application/xml")
	@GetMapping("/{id}")
	public Employee find(@PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("finding a employee with id " + id);
		Employee employee = employeeService.findById(id);
		logger.info("employee found with id " + id + " is" + employee.getName());
		return employee;
	}
	 @ApiOperation(value = "Read all employee details from repository.",produces = "application/xml")
	    @ApiResponses(value = {
	    		@ApiResponse(code=200,message = "successfully retrieved list of employees"),
	    		@ApiResponse(code=401,message = "You are not authorized to view the repository"),
	    		@ApiResponse(code=403,message = "Accessing the resources you are trying to reach is forbidden"),
	    		@ApiResponse(code=404,message = "The resources you were trying to reach is not found")
	    })
	@GetMapping()
	public List<Employee> findAll() {
		logger.info("finding all employees");
		return employeeService.findAll();
	}
     @ApiOperation(value = "adding a new employee",produces = "appication/xml")
	@PostMapping()
	public Employee insert(@RequestBody Employee employee) {
		logger.info("inserting a employee with " + employee.getName());
		return employeeService.save(employee);
	}
    @ApiOperation(value="edit employee",produces="application/xml")
	@PutMapping()
	public Employee edit(@RequestBody Employee employee , @PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("editing a employee with " + employee.getName());
		return employeeService.update(employee);
	}
   @ApiOperation(value="delete particular employee",produces = "application/xml")
	@DeleteMapping("/{id}")
	public Employee delete(@PathVariable int id) throws InvalidEmployeeIdException {
		logger.info("deleting a employee with id " + id);
		return employeeService.delete(id);
	}
}
