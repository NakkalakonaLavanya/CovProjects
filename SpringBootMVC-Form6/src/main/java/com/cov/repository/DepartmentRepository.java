package com.cov.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cov.beans.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	Optional<Department> findById(int id);

}
