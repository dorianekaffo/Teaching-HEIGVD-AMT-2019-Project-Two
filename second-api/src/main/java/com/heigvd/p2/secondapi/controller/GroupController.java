package com.heigvd.p2.secondapi.controller;

import com.heigvd.p2.secondapi.model.Group;
import com.heigvd.p2.secondapi.model.Person;
import com.heigvd.p2.secondapi.service.GroupService;
import com.heigvd.p2.secondapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<Page<Group>> getAll(
            Pageable pgble
    ) {
        return new ResponseEntity(this.groupService.getAll(pgble), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> create(
            @RequestBody Group group
    ) {
        return new ResponseEntity<>(this.groupService.create(group), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> get(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity(this.groupService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> update(
            @PathVariable("id") Long id,
            @RequestBody Group group
    ) {
        return new ResponseEntity(this.groupService.update(id, group), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id") Long id) {
        this.groupService.delete(id);
        return new ResponseEntity<>("Entité Supprimé", HttpStatus.OK);
    }
}
