package com.cov.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Employee {
	@ApiModelProperty(notes="Auto generated Employee ID by database")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@ApiModelProperty(notes="name of the employee")
	String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
  //@JoinColumn(name = "dept_id", nullable = false)
	@JsonIgnore
	 Department department;

	public Employee(int id, String name,  Department department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public Employee() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + "]";
	}

}
