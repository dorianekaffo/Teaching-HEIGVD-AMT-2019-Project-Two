package com.heigvd.p2.secondapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<Membership> memberships;

    // -- Constructeur(s)
    public Person() { }

    public Person(String name) {
        this.name = name;
    }

    // -- Getter(s) et Setter(s)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}