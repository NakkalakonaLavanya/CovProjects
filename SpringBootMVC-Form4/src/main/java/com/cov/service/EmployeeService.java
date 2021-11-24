package com.cov.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cov.beans.Employee;

import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;

import com.cov.repository.EmployeeRepository;

@Service
public class EmployeeService {
	Logger logger = Logger.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> findAll() {
		logger.info("finding all employees");

		return employeeRepository.findAll();

	}

	public Employee findById(int id) throws InvalidEmployeeIdException {
		logger.info("finding employee with id: " + id);
		Optional<Employee> empOptional = employeeRepository.findById(id);
		InvalidEmployeeIdException invalidEmployeeIdException = new InvalidEmployeeIdException("Employee id not found");
		logger.warn(invalidEmployeeIdException);
		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id " + id + " not existing in repository");
		}
		Employee employee = empOptional.get();
		logger.info("employee found with id " + id + "is" + employee.getName()+" "+employee.getDepartment());

		return empOptional.get();

	}

	public Employee save(Employee employee) {
		logger.info("inserting a employee");
		InvalidEmployeeIdException invalidemployeeIdException = new InvalidEmployeeIdException("Employee id not found");
		logger.warn(invalidemployeeIdException);
		return employeeRepository.save(employee);

	}

	public Employee update(Employee employee) throws InvalidEmployeeIdException {
		logger.info("updating employee ");
		Optional<Employee> empOptional = employeeRepository.findById(employee.getId());
		InvalidEmployeeIdException invalidEmployeeIdException = new InvalidEmployeeIdException("Person id not found");
		logger.warn(invalidEmployeeIdException);
		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id" + employee.getId() + "not existing in reposiotory");
		}
		logger.info("employee updated " + "is" + employee.getName()+" "+employee.getDepartment());
		return employeeRepository.save(employee);
	}

	public Employee delete(int id) throws InvalidEmployeeIdException {
		logger.info("deleting employee with id " + id);
		Optional<Employee> empOptional = employeeRepository.findById(id);
		InvalidEmployeeIdException invalidEmployeeIdException = new InvalidEmployeeIdException("Person id not found");
		logger.warn(invalidEmployeeIdException);
		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id " + id + "not existing in repository");
		}
		Employee employee = empOptional.get();
		employeeRepository.deleteById(id);
		logger.info("employee deleted " + "is with id " + id + " " + employee.getName()+" "+employee.getDepartment());
		return employee;
	}

	public List<Employee> findAllByDeptno(int deptno) throws InvalidDepartmentIdException {

		List<Employee> employee = employeeRepository.findAllEmployeeDeptno(deptno);
		if (employee.isEmpty()) {
			throw new InvalidDepartmentIdException("Department Id " + deptno + "not existing in repository");
		}

		return employee;

	}

}
