package com.cov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cov.beans.Department;
import com.cov.beans.Employee;
import com.cov.exception.InvalidDepartmentIdException;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.service.DepartmentService;
import com.cov.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "regemp", method = RequestMethod.GET)
	public ModelAndView newEmployee() {
		ModelAndView modelAndView = new ModelAndView("regEmployee", "employee", new Employee());
		modelAndView.addObject("departmentService", departmentService);
		return modelAndView;
	}

	@RequestMapping(value = "regemp", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("employee") Employee emp) throws InvalidEmployeeIdException {
		System.out.println("Trying to insert employee : " + emp);
		System.out.println("Department : " + emp.getDepartment());
		ModelAndView modelAndView = new ModelAndView("showEmployees");
		employeeService.save(emp);
		modelAndView.addObject("emps", employeeService.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "getemps", method = RequestMethod.GET)
	public ModelAndView findEmployeeAll() throws InvalidEmployeeIdException {
		ModelAndView modelAndView = new ModelAndView("showEmployees");
		List<Employee> emps = employeeService.findAll();
		List<Department> depts = departmentService.findAll();

		modelAndView.addObject("emps", emps);
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("departmentService", departmentService);
		modelAndView.addObject("department", new Department());
		modelAndView.addObject("employeeService", employeeService);
		return modelAndView;
	}

	@RequestMapping(value = "editEmp", method = RequestMethod.GET)
	public ModelAndView editEmp(@RequestParam int id) throws InvalidEmployeeIdException {
		Employee empTOEdit = employeeService.findById(id);
		ModelAndView modelAndView = new ModelAndView("editEmp", "empToEdit", empTOEdit);
		modelAndView.addObject("departmentService", departmentService);
		return modelAndView;
	}

	@RequestMapping(value = "updateEmp", method = RequestMethod.POST)
	public ModelAndView updateEditEmp(@ModelAttribute("empToEdit") Employee employee)
			throws InvalidEmployeeIdException {
		employeeService.update(employee);
		ModelAndView modelAndView = new ModelAndView("redirect:" + "getemps");
		System.out.println("Employee Updated successfully with id : " + employee.getId());
		return modelAndView;
	}

	@RequestMapping(value = "deleteEmp")
	public ModelAndView deleteEmp(@RequestParam int id) throws InvalidEmployeeIdException {
		employeeService.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:" + "getemps");
		return modelAndView;
	}

	@RequestMapping(value = "getAllEmp", method = RequestMethod.POST)
	public ModelAndView findAllEmployeeByDept(@RequestParam int deptid) throws InvalidDepartmentIdException {

		ModelAndView modelAndView = new ModelAndView("showEmployeeByDept");
		modelAndView.addObject("departmentService", departmentService);
		List<Employee> emps = employeeService.findAllByDeptno(deptid);
		modelAndView.addObject("emps",emps);

		return modelAndView;

	}

}
