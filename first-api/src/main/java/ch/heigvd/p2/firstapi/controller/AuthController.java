package ch.heigvd.p2.firstapi.controller;

import ch.heigvd.p2.firstapi.dto.Credentials;
import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.security.CustomUserDetails;
import ch.heigvd.p2.firstapi.service.AuthService;
import ch.heigvd.p2.firstapi.service.UserService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Credentials credentials
    ) {
        try {
            return new ResponseEntity<>(authService.login(
                    credentials.getEmail(), credentials.getPassword()), HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>("Email ou mot de passe incorrecte", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            Principal principal,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        try {
            User user = this.userService.get(((CustomUserDetails)token.getPrincipal()).getEmail());
            return new ResponseEntity<>("Déconnexion effectuée", HttpStatus.OK);
        } catch (RessourceNotFoundException e) {
            return new ResponseEntity<>("Erreur d'authentification", HttpStatus.UNAUTHORIZED);
        }

    }
}
