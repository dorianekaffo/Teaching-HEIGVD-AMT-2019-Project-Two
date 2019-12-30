package com.heigvd.p2.secondapi.repository;

import com.heigvd.p2.secondapi.model.Group;
import com.heigvd.p2.secondapi.model.Membership;
import com.heigvd.p2.secondapi.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MembershipRepository extends PagingAndSortingRepository<Membership, Long> {

    Optional<Membership> findByPersonAndGroup(Person person, Group group);

}