package ch.heigvd.p2.firstapi.security;

import ch.heigvd.p2.firstapi.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.default_validity_period}")
    private int JWT_EXPIRY_PERIOD_IN_MS;

    public String generateToken(User user) {

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.MILLISECOND, JWT_EXPIRY_PERIOD_IN_MS);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate.getTime())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

    }

    public String generateToken(User user, int validityPeriod) {

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.MILLISECOND, validityPeriod);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate.getTime())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUidFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
