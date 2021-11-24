package com.cov.service;


import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cov.beans.Department;
import com.cov.beans.Employee;
import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.repository.DepartmentRepository;

@Service
public class DepartmentService {
	Logger logger = Logger.getLogger(DepartmentService.class);
	@Autowired
	DepartmentRepository departmentRepository;

	public List<Department> findAll() {	
		logger.info("finding all departments");
//		List<Department> department=new ArrayList<Department>();
		return departmentRepository.findAll();

	}

	public Department findById(int id) throws InvalidDepartmentIdException {
		logger.info("finding employee with id: " + id);
		Optional<Department> deptOptional = departmentRepository.findById(id);
		InvalidDepartmentIdException invalidDepartmentIdException = new InvalidDepartmentIdException("Employee id not found");
		logger.warn(invalidDepartmentIdException);
		if (!deptOptional.isPresent()) {
			throw new InvalidDepartmentIdException("Department Id " + id + " not existing in repository");
		}
		Department department = deptOptional.get();
		logger.info("department found with id " + id + "is" + department.getName());
		return deptOptional.get();

	}

	public Department save(Department department) {
		logger.info("inserting a departrment");
		InvalidDepartmentIdException invalidDepartmentIdException = new InvalidDepartmentIdException("Employee id not found");
		logger.warn(invalidDepartmentIdException);

		return departmentRepository.save(department);

	}

	public Department update(Department department) throws InvalidDepartmentIdException {
		logger.info("updating department");
		Optional<Department> deptOptional = departmentRepository.findById(department.getId());
		InvalidDepartmentIdException invalidDepartmentIdException = new InvalidDepartmentIdException("Person id not found");
		logger.warn(invalidDepartmentIdException);
		if (!deptOptional.isPresent()) {
			throw new InvalidDepartmentIdException(
					"Department Id" + department.getId() + "not existing in reposiotory");
		}
		logger.info("department updated " + "is" + department.getName());
		return departmentRepository.save(department);
	}

	public Department delete(int id) throws InvalidDepartmentIdException {
		logger.info("deleting department with id " + id);
		Optional<Department> deptOptional = departmentRepository.findById(id);
		InvalidDepartmentIdException invalidDepartmentIdException = new InvalidDepartmentIdException("Department id not found");
		logger.warn(invalidDepartmentIdException);
		if (!deptOptional.isPresent()) {
			throw new InvalidDepartmentIdException("Department Id " + id + "not existing in repository");
		}
		Department department = deptOptional.get();
		    departmentRepository.deleteById(id);
		logger.info("department deleted " + "is with id " + id + " " + department.getName());
		return department;
	}
}
