package com.heigvd.p2.secondapi.model;

import javax.persistence.*;


@Entity
public class Membership {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    // -- Constructeur(s)
    public Membership() { }

    public Membership(Person person, Group group) {
        this.person = person;
        this.group = group;
    }

    // -- Getter(s) et Setter(s)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
