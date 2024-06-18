package com.example.jpa_sample;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa_sample.person.domain.Person;
import com.example.jpa_sample.person.repository.PersonRepository;

@SpringBootTest
class JpaSampleApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Test
	void contextLoads() {

		Person person = new Person();
		person.setId((long) 1);
		person.setName("홍길동");
		person.setEmail("hong@email.com");
		personRepository.save(person);

		person = new Person();
		person.setId((long) 2);
		person.setName("이순신");
		person.setEmail("soonsin@email.com");
		personRepository.save(person);

		Person FinePerson = personRepository.findById(person.getId()).get();
		List<Person> personList = personRepository.findAll();

		for (int i = 0; i < personList.size(); i++) {
			Person person1 = personList.get(i);
			System.out.println(
					"ID:" + person1.getId() + " 이름:" + person1.getName() + " 메일 주소:" + person1.getEmail());
		}

		Assertions.assertThat(person.getName()).isEqualTo(FinePerson.getName());

	}

}
