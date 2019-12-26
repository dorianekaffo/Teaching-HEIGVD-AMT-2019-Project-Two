package com.heigvd.p2.secondapi.repository;

import com.heigvd.p2.secondapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
