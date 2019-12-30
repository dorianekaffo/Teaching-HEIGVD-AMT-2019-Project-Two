package com.heigvd.p2.secondapi.repository;


import com.heigvd.p2.secondapi.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}