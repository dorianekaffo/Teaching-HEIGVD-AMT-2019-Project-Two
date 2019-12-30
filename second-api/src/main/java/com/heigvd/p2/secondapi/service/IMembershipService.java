package com.heigvd.p2.secondapi.service;

import com.heigvd.p2.secondapi.model.Group;
import com.heigvd.p2.secondapi.model.Membership;
import com.heigvd.p2.secondapi.model.Person;

public interface IMembershipService {

    public Membership associate(Person person, Group group);

    public void dissociate(Person person, Group group);
}
