package ch.heigvd.p2.firstapi.repository;

import ch.heigvd.p2.firstapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
