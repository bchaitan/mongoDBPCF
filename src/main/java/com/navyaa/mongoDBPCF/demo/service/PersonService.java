package com.navyaa.mongoDBPCF.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.navyaa.mongoDBPCF.demo.model.Person;

@Service
public class PersonService {
	
	private List<Person> personList = new ArrayList<>();
	
	public List<Person> getAllPersons() {
		return personList;
	}
	
	public List<Person> findByFirstName(String firstName) {
		System.out.println("Inside findByFirstName @@@" + firstName);
		List<Person> filteredList = personList.parallelStream().filter(s -> s.getFirstName().equalsIgnoreCase(firstName)).collect(Collectors.toList());
		System.out.println("length of filteredList" + filteredList.size());
		filteredList.forEach(e -> System.out.println("Person is " + e));
		return filteredList;
	}

	public List<Person> findByLastName(String lastName) {
		System.out.println("Inside findByLastName @@@ " + lastName);
		List<Person> filteredList =  personList.parallelStream().filter(s -> s.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toList());
		System.out.println("length of filteredList" + filteredList.size());
		filteredList.forEach(e -> System.out.println("Person is " + e));
		return filteredList;

	}

	public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
		List<Person> personsList = new ArrayList<>();
		Person person = personList.parallelStream().filter(s -> (s.getFirstName().equalsIgnoreCase(firstName)) && (s.getLastName().equalsIgnoreCase(lastName))).findFirst().get();
		personsList.add(person);
		return personsList;
	}

	
	public Person addPerson(Person p) {
		boolean status =personList.add(p);
		if (status) {
			return p;
		}
		else {
			return null;
		}
			
	}
	public Person findById(Long id) {
		return personList.parallelStream().filter(e -> e.getId().longValue()==id.longValue()).findFirst().get();
	}
	
	public Person updatePerson(Long id, Person p) {
		Person oldPerson = findById(id);
		if (null != oldPerson) {
			oldPerson.setAge(p.getAge());
			oldPerson.setFirstName(p.getFirstName());
			oldPerson.setLastName(p.getLastName());
			return oldPerson;
		}else {
			return null;
		}			
	}
	
	public void deletePerson(Long id) {
		Person oldPerson = findById(id);
		personList.remove(oldPerson);
	}

	public void deleteAllPersons() {
		personList.clear();
	}
}
