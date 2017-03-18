package com.finartz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
@WebAppConfiguration
public class PersonControllerTest {
	
	private final String SUCCESS = "SUCCESS";
	private final String FAIL = "FAIL";
	private MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext webApplicationContext;
	
	@Autowired
	private IPersonService personService;
	
	@Before
	public void setup() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
	}


	private String convertString(Person person) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(person);
	}

	@Test
	public void testFindAll() throws Exception {
		mockMvc
	        .perform(get("/core/findAll"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.status").value(SUCCESS));
	}
	
	@Test
	public void testSave() throws Exception {
		Person person = new Person("ali","veli");
		String personAsJson = convertString(person);
		mockMvc
			.perform(post("/core/save")
					.content(personAsJson)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value(SUCCESS));
	}
	
	@Test
	public void testUpdate() throws Exception {
		List<Person> personList = personService.findAll();
		if(!personList.isEmpty()) {
			Person person = personList.get(0);
			person.setSurname("updated!");
			String personAsJson = convertString(person);
			mockMvc
				.perform(post("/core/update")
						.content(personAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(SUCCESS));
		}
	}
	
	@Test
	public void testRemove() throws Exception {
		List<Person> personList = personService.findAll();
		if(!personList.isEmpty()) {
			Person person = personList.get(0);
			mockMvc	
				.perform(delete("/core/remove/" + person.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(SUCCESS));
		}
	}
}
