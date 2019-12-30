package ch.heigvd.p2.firstapi.repository;

import ch.heigvd.p2.firstapi.model.Code;
import ch.heigvd.p2.firstapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findByOwnerAndCodeAndExpired(User user, String code, boolean expired);
}
