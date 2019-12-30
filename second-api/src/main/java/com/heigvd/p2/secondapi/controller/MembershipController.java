package com.heigvd.p2.secondapi.controller;

import com.heigvd.p2.secondapi.dto.CreateMembership;
import com.heigvd.p2.secondapi.model.Group;
import com.heigvd.p2.secondapi.model.Membership;
import com.heigvd.p2.secondapi.model.Person;
import com.heigvd.p2.secondapi.service.GroupService;
import com.heigvd.p2.secondapi.service.MembershipService;
import com.heigvd.p2.secondapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private PersonService personService;

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Membership> associate(
        @RequestBody CreateMembership createMembership
    ) {
        Person person = this.personService.get(createMembership.getPersonId());
        Group group = this.groupService.get(createMembership.getGroupId());
        return new ResponseEntity<>(this.membershipService.associate(person, group), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> dissociate(
            @RequestBody CreateMembership createMembership
    ) {
        Person person = this.personService.get(createMembership.getPersonId());
        Group group = this.groupService.get(createMembership.getGroupId());
        this.membershipService.dissociate(person, group);
        return new ResponseEntity<>("Entité supprimé", HttpStatus.OK);
    }
}
