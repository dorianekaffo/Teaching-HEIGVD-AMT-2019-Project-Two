package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.enums.Role;
import ch.heigvd.amt.p2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
    Page<User> findByOwner(String email, Pageable pgble);
    boolean existsByEmailAndOwner(String userId, String ownerId);
    boolean existsByEmailAndRole(String userId, Role role);
}