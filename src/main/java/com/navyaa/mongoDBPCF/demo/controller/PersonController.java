package com.navyaa.mongoDBPCF.demo.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.navyaa.mongoDBPCF.demo.model.Person;
import com.navyaa.mongoDBPCF.demo.service.PersonMongoDBService;
import com.navyaa.mongoDBPCF.demo.service.PersonService;

@RequestMapping("/personsAPI")
@RestController
public class PersonController {

	@Autowired
	PersonMongoDBService personMongoDBService;
	
	@RequestMapping(value="/person", method=RequestMethod.GET)
	public Iterable<Person> getAllPersons(@RequestParam(name="firstName", required = false) String firstName, @RequestParam(name="lastName",required = false) String lastName){
		
		if (null != firstName && null != lastName) {
			return personMongoDBService.findByFirstNameAndLastName(firstName, lastName);
		}else if (null == firstName && null != lastName) {
			return personMongoDBService.findByLastName(lastName);
		}else if (null != firstName && null == lastName) {
			return personMongoDBService.findByFirstName(firstName);
		}else {
			return personMongoDBService.getAllPersons();
		}

		
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.GET)
	public Person getAPerson(@PathVariable(name = "id") Long  id){
		return personMongoDBService.findById(id);
	}

	
	@RequestMapping(value="/person", method=RequestMethod.POST)
	public Person addAPerson(@RequestBody Person p){
		return personMongoDBService.addPerson(p);
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.PUT)
	public Person addAPerson(@PathVariable(name="id") Long id, @RequestBody Person p){
		return personMongoDBService.updatePerson(id, p);
	}

	
	@RequestMapping(value="/person/{id}", method=RequestMethod.DELETE)
	public String deletePerson(@PathVariable(name="id") Long id){
		personMongoDBService.deletePerson(id);
		return "Person with id " + id + " is deleted";
	}
	
	@RequestMapping(value="/person", method=RequestMethod.DELETE)
	public String deleteAllPerson(){
		personMongoDBService.deleteAllPersons();;
		return "All Persons are deleted";
	}

}
