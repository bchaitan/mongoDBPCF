package com.navyaa.mongoDBPCF.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.navyaa.mongoDBPCF.demo.model.Person;


public interface PersonMongoDBRepository extends MongoRepository<Person, Long>{
	
	List<Person> findByFirstName(String firstName);
	List<Person> findByLastName(String lastName);
	Person findByFirstNameAndLastName(String firstName, String lastName);

}
