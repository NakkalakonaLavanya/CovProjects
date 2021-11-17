package com.cov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cov.beans.Department;
import com.cov.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByDepartment(Department department);

}
