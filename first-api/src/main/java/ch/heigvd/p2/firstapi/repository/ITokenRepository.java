package ch.heigvd.p2.firstapi.repository;

import ch.heigvd.p2.firstapi.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepository extends JpaRepository<Token, Long> {
}
