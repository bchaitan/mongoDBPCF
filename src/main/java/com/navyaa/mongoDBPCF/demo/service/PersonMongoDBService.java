package com.navyaa.mongoDBPCF.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navyaa.mongoDBPCF.demo.model.Person;
import com.navyaa.mongoDBPCF.demo.repository.PersonMongoDBRepository;

@Service
public class PersonMongoDBService {
	
	@Autowired
	private PersonMongoDBRepository personMongoDBRepository;
	
	public List<Person> getAllPersons() {
		return personMongoDBRepository.findAll();
	}
	
	public List<Person> findByFirstName(String firstName) {
		return personMongoDBRepository.findByFirstName(firstName);
	}

	public List<Person> findByLastName(String lastName) {
		return personMongoDBRepository.findByLastName(lastName);

	}

	public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsList = new ArrayList<>();
		Person person = personMongoDBRepository.findByFirstNameAndLastName(firstName, lastName);
		personsList.add(person);
		return personsList;
	}

	
	public Person addPerson(Person p) {
		return personMongoDBRepository.save(p);			
	}
	public Person findById(Long id) {
		return personMongoDBRepository.findById(id).get();
	}
	
	public Person updatePerson(Long id, Person p) {
		Person oldPerson = findById(id);
		if (null != oldPerson) {
			oldPerson.setAge(p.getAge());
			oldPerson.setFirstName(p.getFirstName());
			oldPerson.setLastName(p.getLastName());
			personMongoDBRepository.save(oldPerson);
			return oldPerson;
		}else {
			return null;
		}			
	}
	
	public void deletePerson(Long id) {
		personMongoDBRepository.deleteById(id);
	}

	public void deleteAllPersons() {
		personMongoDBRepository.deleteAll();
	}
}
