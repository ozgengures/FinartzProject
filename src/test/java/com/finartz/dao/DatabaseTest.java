package com.finartz.dao;

import com.finartz.service.IPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
public class DatabaseTest {

	@Autowired
	private IPersonService personService;
	
	@Test
	public void testConnection() {
		try {
			personService.findById("1");
		} catch (Exception e) {
			fail("Database connection failed!");
		}
	}
}
