package com.finartz.validator;

import com.finartz.exception.PersonException;
import com.finartz.exception.ValidationException;
import com.finartz.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
@WebAppConfiguration
public class BeforeSaveValidatorTest {
	
	@Autowired
	private BeforeSaveValidator beforeSaveValidator;

	@Test
	@SuppressWarnings("deprecation")
	public void testOnBeforeSaveExpectedSuccess() {
		Person person = new Person("isim", "soyisim");
		
		try {
			beforeSaveValidator.onBeforeSave(person, null);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test (expected = ValidationException.class)
	@SuppressWarnings("deprecation")
	public void testOnBeforeSaveExpectedMin3Validation() {
		Person person = new Person("Gonul", "","05342223561");
		beforeSaveValidator.onBeforeSave(person, null);
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void testOnBeforeSaveExpectedNotEmptyValidation() {
		Person person = new Person("", "","");
		
		try {
			beforeSaveValidator.onBeforeSave(person, null);
			fail();
		} catch (PersonException le) {
			//success
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
