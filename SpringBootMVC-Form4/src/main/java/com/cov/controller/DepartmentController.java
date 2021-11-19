
package com.cov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cov.beans.Department;

import com.cov.exception.InvalidDepartmentIdException;

import com.cov.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "regDept", method = RequestMethod.GET)
	         public ModelAndView newDepartment() {
		ModelAndView modelAndView = new ModelAndView("newDept", "department", new Department());

		return modelAndView;
	}

	@RequestMapping(value = "regDept", method = RequestMethod.POST)
	public ModelAndView saveDepartment(@ModelAttribute Department dept) throws InvalidDepartmentIdException {
		ModelAndView modelAndView = new ModelAndView("savedDepartment");
		modelAndView.addObject("dept", departmentService.save(dept));
		return modelAndView;
	}

	@RequestMapping("getDept")
	public ModelAndView findDepartment() {
		ModelAndView modelAndView = new ModelAndView("showDepartment", "depts", departmentService.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "editDept", method = RequestMethod.GET)
	public ModelAndView editDept(@RequestParam int id) throws InvalidDepartmentIdException {
		Department deptToEdit = departmentService.findById(id);
		System.out.println("Department to edit : " + deptToEdit);
		ModelAndView modelAndView = new ModelAndView("editDept", "deptToEdit", deptToEdit);
		//System.out.println("ModelAndView : " + modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "updateDept", method = RequestMethod.POST)
	public ModelAndView updateDept(@ModelAttribute("deptToEdit") Department department)
			throws InvalidDepartmentIdException {
		departmentService.update(department);
		ModelAndView modelAndView = new ModelAndView("redirect:getDept");
		return modelAndView;

	}

}
