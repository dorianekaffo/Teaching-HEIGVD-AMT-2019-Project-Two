package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
    Page<User> findByOwnerEmail(String email, Pageable pgble);
    boolean existsByEmailAndOwnerEmail(String userId, String ownerId);
}