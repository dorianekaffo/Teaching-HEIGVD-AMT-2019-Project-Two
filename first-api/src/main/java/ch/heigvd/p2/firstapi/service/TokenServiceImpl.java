package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.model.Token;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.ITokenRepository;
import ch.heigvd.p2.firstapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl {

    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public Token create(User user) {
        String tokenString = tokenProvider.generateToken(user);
        Token token = new Token(user, tokenString);
        return this.tokenRepository.save(token);
    }

    public boolean validateToken(Token token) {
        return this.tokenProvider.validateToken(token.getToken());
    }

}
