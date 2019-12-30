package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.model.User;

import ch.heigvd.p2.firstapi.security.CustomUserDetails;
import ch.heigvd.p2.firstapi.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public String login(String email, String password) throws RessourceNotFoundException {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user= this.userService.get(email);

            if (passwordEncoder.matches(password, user.getPassword())) {

                CustomUserDetails userDetails = new CustomUserDetails(user);
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                String token = tokenService.generateToken(user);

                // -- Ajout de l'objet "Authentication" dans le contexte sécuritaire
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return token;
            }

        return null;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, User user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(
                    request, response, auth);
        }
    }

}
