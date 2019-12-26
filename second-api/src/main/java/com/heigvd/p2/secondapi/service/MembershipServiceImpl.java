package com.heigvd.p2.secondapi.service;

import com.heigvd.p2.secondapi.model.Group;
import com.heigvd.p2.secondapi.model.Membership;
import com.heigvd.p2.secondapi.model.Person;
import com.heigvd.p2.secondapi.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    public MembershipRepository membershipRepository;

    @Override
    public Membership associate(Person person, Group group) {
        return this.membershipRepository.save(new Membership(person, group));
    }

    @Override
    public void dissociate(Person person, Group group) {
        Membership membership = this.membershipRepository
                .findByPersonAndGroup(person, group).orElse(null);
        this.membershipRepository.delete(membership);
    }
}
