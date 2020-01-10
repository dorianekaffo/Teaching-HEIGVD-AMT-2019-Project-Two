package ch.heigvd.amt.p2.endpoints.api;

import ch.heigvd.amt.p2.endpoints.model.Credentials;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.model.User;
import ch.heigvd.amt.p2.security.CustomUserDetails;
import ch.heigvd.amt.p2.service.AuthService;
import ch.heigvd.amt.p2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-01T15:06:39.162Z")

@Controller
public class AuthApiController implements AuthApi {

    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> login(@ApiParam(value = "Les identifiants de l'utilisateur" ,required=true )  @Valid @RequestBody Credentials credentials) {

        String token = "";
        try {
            token = authService.login(
                    credentials.getEmail(), credentials.getPassword());
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Email ou mot de passe incorrecte", HttpStatus.UNAUTHORIZED);
        };
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    public ResponseEntity<String> logout(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization, Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        try {
            User user = this.userService.get(((CustomUserDetails)token.getPrincipal()).getEmail());
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Erreur d'authentification", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Déconnexion réussie", HttpStatus.OK);
    }

}
