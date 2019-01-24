package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class TestControillerTest {
	private TestController testController;
	
	@Mock
	private PersonRepository personRepository;
	
	private ModelMapper modelMapper;
	
	@Before
	public void setup() {
		modelMapper = new ModelMapper();
		testController = new TestController(personRepository, modelMapper);
	}

	@Test
	public void test_hello() {
		assertEquals("Hi Guys", testController.hello());
	}
	
	@Test
	public void test_createPerson() {
		PersonDTO personDTO = PersonDTO.builder()
				.age(1L)
				.email("abc@test.com")
				.name("cognier")
				.build();
		
		Person givenPerson = modelMapper.map(personDTO, Person.class);
		Person person = Person.builder()
				.id(100L)
				.age(1L)
				.email("abc@test.com")
				.name("cognier")
				.build();
		when(personRepository.save(givenPerson)).thenReturn(person);
		Person expectedPerson = Person.builder()
				.id(100L)
				.age(1L)
				.email("abc@test.com")
				.name("i dont want to show")
				.build();
		assertEquals(expectedPerson, testController.createPerson(personDTO));
	}
}
