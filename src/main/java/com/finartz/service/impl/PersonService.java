package com.finartz.service.impl;

import com.finartz.exception.InconsistentException;
import com.finartz.model.Person;
import com.finartz.repository.IPersonRepository;
import com.finartz.service.IPersonService;
import com.finartz.statics.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {
	
	private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

	@Autowired
	private IPersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findById(String id) {
		return personRepository.findOne(id);
	}

	@Override
	public Person save(Person person) {
		logger.info("Person({}) will be save.", person);
		return personRepository.save(person);
	}

	@Override
	public Person update(Person person) {
		boolean exist = personRepository.exists(person.getId());
		if(exist) {
			logger.info("Person({}) will be update.", person);
			return personRepository.save(person);
		} else {
			logger.error("Person({}) does not exist!", person);
			throw new InconsistentException(Constants.BOYLE_BIR_KULLANICI_YOK);
		}
	}

	@Override
	public void remove(String id) {
		boolean exist = personRepository.exists(id);
		if(exist) {
			logger.info("Person({}) will be remove.", id);
			personRepository.delete(id);
		} else {
			logger.error("Person({}) does not exist!" , id);
			throw new InconsistentException(Constants.BOYLE_BIR_KULLANICI_YOK);
		}
	}
}
