package com.heigvd.p2.secondapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="department")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CASCADE)
    private Set<Membership> memberships;

    // -- Constructeur
    public Group() {}

    public Group(String name) {
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
}
