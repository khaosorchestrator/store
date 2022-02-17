package com.multitenant.store.controller;

import com.multitenant.store.entities.Person;
import com.multitenant.store.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/create")
    public ResponseEntity<?> save(@RequestBody Person person) {
        service.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getAll() throws SQLException {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Person>> deleteByName(@PathVariable String name) throws SQLException {
        service.delete(name);
        return ResponseEntity.noContent().build();
    }
}
