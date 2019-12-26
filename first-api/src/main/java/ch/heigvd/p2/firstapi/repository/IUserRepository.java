package ch.heigvd.p2.firstapi.repository;

import ch.heigvd.p2.firstapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{

}