
package com.cov.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cov.beans.Employee;
import com.cov.exception.InvalidEmployeeIdException;
import com.cov.service.DepartmentService;
import com.cov.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "regEmp", method = RequestMethod.GET)
	public ModelAndView newEmployee() {
		ModelAndView modelAndView = new ModelAndView("regEmployee", "employee", new Employee());
		modelAndView.addObject("departmentService", departmentService);
		return modelAndView;
	}

	@RequestMapping(value = "regEmp", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee emp) throws InvalidEmployeeIdException {
		System.out.println("Employee to be inserted : "+emp);
		System.out.println("Department : "+emp.getDepartment());
		ModelAndView modelAndView = new ModelAndView("redirect:getEmp");
		modelAndView.addObject("emp", employeeService.save(emp));
		return modelAndView;
	}

	@RequestMapping("getEmp")
	public ModelAndView findEmployee() {
		ModelAndView modelAndView = new ModelAndView("showEmployees", "emps", employeeService.findAll());
		return modelAndView;
	}
	@RequestMapping(value = "editEmp", method = RequestMethod.GET)
	public ModelAndView editEmp(@RequestParam int id) throws InvalidEmployeeIdException {
		Employee empToEdit = employeeService.findById(id);
		System.out.println("Employee to edit : " + empToEdit);
		ModelAndView modelAndView = new ModelAndView("editEmp", "empToEdit", empToEdit);
		System.out.println("ModelAndView : " + modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "updateEmp", method = RequestMethod.POST)
	public ModelAndView updateEditEmployee(@ModelAttribute("empToEdit") Employee employee)
			throws InvalidEmployeeIdException {
		employeeService.update(employee);
		ModelAndView modelAndView = new ModelAndView("redirect:"+"getEmp");
		
		return modelAndView;

	}

	@RequestMapping(value = "deleteEmp")
	public ModelAndView deleteEmp(@RequestParam int id) throws InvalidEmployeeIdException {
		employeeService.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:getEmp");
		return modelAndView;
	}



}
