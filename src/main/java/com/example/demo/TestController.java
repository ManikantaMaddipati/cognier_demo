package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hi Guys";
	}
	
	@PostMapping("/person")
	public Person createPerson(@RequestBody PersonDTO personDTO) {
		Person person =  Person.builder()
				.name(personDTO.getName())
				.age(personDTO.getAge())
				.email(personDTO.getEmail())
				.build();
		return personRepository.save(person);
	}
}
