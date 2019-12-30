package com.heigvd.p2.secondapi.repository;

import com.heigvd.p2.secondapi.model.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {

}
