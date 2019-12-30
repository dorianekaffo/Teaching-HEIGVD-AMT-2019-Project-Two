package ch.heigvd.p2.firstapi.repository;

import ch.heigvd.p2.firstapi.enums.RoleType;
import ch.heigvd.p2.firstapi.model.Role;
import ch.heigvd.p2.firstapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByOwner(User user);
    Set<Role> findByOwnerEmail(String userEmail);
    boolean existsByTypeAndOwnerEmail(RoleType type, String email);
}
