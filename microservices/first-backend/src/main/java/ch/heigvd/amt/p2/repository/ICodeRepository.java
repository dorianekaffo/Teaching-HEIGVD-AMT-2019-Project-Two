package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Code;
import ch.heigvd.amt.p2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findByOwnerAndCodeAndExpired(User user, String code, boolean expired);
}
