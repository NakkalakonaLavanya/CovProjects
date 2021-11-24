
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
import com.cov.beans.Person;
import com.cov.exception.InvalidPersonIdException;
import com.cov.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	static Logger logger = Logger.getLogger(PersonController.class);
	@Autowired
	PersonService personService;

	@GetMapping("/{id}")
	public Person find(@PathVariable int id) throws InvalidPersonIdException {
		logger.info("finding a person with id " + id);
// System.out.println("data not found"+id);
		Person person = personService.findById(id);
		logger.info("person found with id " + id + " is" + person.getFirstName() + " " + person.getLastName());
		return personService.findById(id);
	}

	@GetMapping()
	public List<Person> findAll() {
		logger.info("finding all persons");
		System.out.println("data not found");
		return personService.findAll();
	}

	@PostMapping()
	public Person insertPerson(@RequestBody Person person) {
		logger.info("inserting a person with " + person.getFirstName() + " " + person.getLastName());
		System.out.println("data not found" + person);
		return personService.insert(person);
	}

	@PutMapping()
	public Person edit(@RequestBody Person person) throws InvalidPersonIdException {
		logger.info("editing a person with " + person.getFirstName() + " " + person.getLastName());
		System.out.println("data not found" + person);
		return personService.update(person);
	}

	@DeleteMapping("/{id}")
	public Person delete(@PathVariable int id) throws InvalidPersonIdException {
		logger.info("deleting a person with id " + id);
		System.out.println("data not found" + id);
		return personService.delete(id);
	}
}
