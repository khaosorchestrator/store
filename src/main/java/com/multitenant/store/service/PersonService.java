package com.multitenant.store.service;

import com.multitenant.store.entities.Person;
import com.multitenant.store.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> getAll() throws SQLException {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public Person getByName(String name) {
        return personRepository.findByName(name);
    }

    public void delete(String name) {
        personRepository.deleteByName(name);
    }
}
