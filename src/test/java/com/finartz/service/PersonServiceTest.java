package com.finartz.service;

import com.finartz.exception.InconsistentException;
import com.finartz.model.Person;
import com.finartz.repository.IPersonRepository;
import com.finartz.service.impl.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
public class PersonServiceTest {

	@InjectMocks
	private PersonService personService;
	
	@Mock
	private IPersonRepository personRepository;
	
	Person person = null;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		person = new Person();
		person.setId("1");
		person.setName("a");
		person.setSurname("b");
		person.setPhoneNumber("3");
	}
	
	@Test
	public void testSave(){
		personService.save(person);
		verify(personRepository, times(1)).save(person);
	}
	
	@Test
	public void testUpdate() {
		when(personRepository.exists(anyString())).thenReturn(true);
		personService.update(person);
		verify(personRepository, times(1)).save(person);
	}

	@Test (expected = InconsistentException.class)
	public void shouldPersonWhenExistThrowException() throws Exception {

		when(personRepository.exists(anyString())).thenReturn(false);
		personService.update(person);
	}

	@Test
	public void testRemove() {
		when(personRepository.exists(anyString())).thenReturn(true);
		personService.remove(anyString());
		verify(personRepository, times(1)).delete(anyString());
	}
	
	@Test
	public void testFindAll() {
		personService.findAll();
		verify(personRepository, times(1)).findAll();
	}
}
