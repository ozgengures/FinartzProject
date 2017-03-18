package com.finartz.service;

import com.finartz.model.Person;

import java.util.List;

public interface IPersonService {

	List<Person> findAll();
	
	Person findById(String id);
	
	Person save(Person person);
	
	Person update(Person person);
	
	void remove(String id);
}
