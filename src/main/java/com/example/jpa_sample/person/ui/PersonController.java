package com.example.jpa_sample.person.ui;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_sample.person.domain.Person;
import com.example.jpa_sample.person.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			return ResponseEntity.ok(person.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public Person createPerson(@RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			Person updatedPerson = person.get();
			updatedPerson.setName(personDetails.getName());
			updatedPerson.setEmail(personDetails.getEmail());
			personRepository.save(updatedPerson);
			return ResponseEntity.ok(updatedPerson);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			personRepository.delete(person.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
