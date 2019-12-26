package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.model.User;

import ch.heigvd.p2.firstapi.security.CustomUserDetailsService;
import ch.heigvd.p2.firstapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public String login(String email, String password) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserDetails userDetails = this.customUserDetailsService
                .loadUserByEmail(email);

            if (passwordEncoder.matches(password, userDetails.getPassword())) {

                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                String token = tokenProvider.generateToken(authentication);

                // TODO(Me): Save token in database

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

            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth.getPrincipal();

            // TODO(Me): Deactivate all saved tokens

        }
    }

}
