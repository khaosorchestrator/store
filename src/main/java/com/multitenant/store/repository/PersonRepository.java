package com.multitenant.store.repository;

import com.multitenant.store.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);
    void deleteByName(String name);
}
