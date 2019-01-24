package com.example.demo;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hi Guys";
	}
	
	@PostMapping("/person")
	public Person createPerson(@RequestBody PersonDTO personDTO) {
		Person person =  modelMapper.map(personDTO, Person.class);
		return personRepository.save(person);
	}
	
	@GetMapping("person/{id}")
	public Person getPerson(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			return person.get();
		}else{
			throw new RuntimeException("Invalid Person id: "+ id); 
		}
	}
	
	@PutMapping("person/{id}")
	public Person updatePerson(@RequestBody PersonDTO personDTO, @PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			Person existingPerson = person.get();
			if(personDTO.getAge() != null) {
				existingPerson.setAge(personDTO.getAge());			
			}
			existingPerson = personRepository.save(existingPerson);
			return existingPerson;
		}else{
			throw new RuntimeException("Invalid Person id: "+ id); 
		}
	}
}
