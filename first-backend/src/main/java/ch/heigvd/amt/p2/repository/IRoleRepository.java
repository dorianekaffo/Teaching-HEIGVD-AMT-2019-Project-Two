package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.enums.RoleType;
import ch.heigvd.amt.p2.model.Role;
import ch.heigvd.amt.p2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByOwner(User user);
    Set<Role> findByOwnerEmail(String userEmail);
    boolean existsByTypeAndOwnerEmail(RoleType type, String email);
}
