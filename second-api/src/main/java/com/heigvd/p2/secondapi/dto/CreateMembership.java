package com.heigvd.p2.secondapi.dto;

public class CreateMembership {

    private Long personId;
    private Long groupId;

    // -- Constructeur
    public CreateMembership() { }

    // -- Getter(s) et Setter(s)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
