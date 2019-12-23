package com.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.project.model.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    List<User> findByEmail(List<Long> userEmail);

    Optional<User> findBySurname(String surname);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndBlocked(String email, Boolean blocked);
}