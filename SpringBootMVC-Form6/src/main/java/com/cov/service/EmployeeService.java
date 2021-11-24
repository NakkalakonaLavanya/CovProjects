package com.cov.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cov.beans.Employee;

import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;

import com.cov.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> findAll() {

		return employeeRepository.findAll();

	}

	public Employee findById(int id) throws InvalidEmployeeIdException {

		Optional<Employee> empOptional = employeeRepository.findById(id);

		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id " + id + " not existing in repository");
		}
		Employee employee = empOptional.get();

		return empOptional.get();

	}

	public Employee save(Employee employee) {

		return employeeRepository.save(employee);

	}

	public Employee update(Employee employee) throws InvalidEmployeeIdException {

		Optional<Employee> empOptional = employeeRepository.findById(employee.getId());

		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id" + employee.getId() + "not existing in reposiotory");
		}

		return employeeRepository.save(employee);
	}

	public Employee delete(int id) throws InvalidEmployeeIdException {
		;
		Optional<Employee> empOptional = employeeRepository.findById(id);

		if (!empOptional.isPresent()) {
			throw new InvalidEmployeeIdException("Employee Id " + id + "not existing in repository");
		}
		Employee employee = empOptional.get();
		employeeRepository.deleteById(id);

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
