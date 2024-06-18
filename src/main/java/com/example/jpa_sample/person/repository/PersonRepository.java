package com.example.jpa_sample.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_sample.person.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
