package com.heigvd.p2.secondapi.controller;

import com.heigvd.p2.secondapi.model.Person;
import com.heigvd.p2.secondapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<Person>> getAll(
            Pageable pgble
    ) {
        return new ResponseEntity(this.personService.getAll(pgble), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> create(
            @RequestBody Person person
    ) {
        return new ResponseEntity<>(this.personService.create(person), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity(this.personService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(
            @PathVariable("id") Long id,
            @RequestBody Person person
    ) {
        return new ResponseEntity(this.personService.update(id, person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id") Long id) {
        this.personService.delete(id);
        return new ResponseEntity<>("Entité Supprimé", HttpStatus.OK);
    }
}
