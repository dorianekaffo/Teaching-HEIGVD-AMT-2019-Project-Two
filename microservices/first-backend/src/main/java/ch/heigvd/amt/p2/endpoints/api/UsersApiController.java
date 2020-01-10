package ch.heigvd.amt.p2.endpoints.api;

import ch.heigvd.amt.p2.endpoints.model.ChangePassword;
import ch.heigvd.amt.p2.endpoints.model.CodeCheck;
import ch.heigvd.amt.p2.endpoints.model.ResetPassword;
import ch.heigvd.amt.p2.endpoints.model.UserId;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.UnauthorizedAccessException;
import ch.heigvd.amt.p2.model.User;
import ch.heigvd.amt.p2.security.CustomUserDetails;
import ch.heigvd.amt.p2.security.TokenService;
import ch.heigvd.amt.p2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-01T15:06:39.162Z")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> block(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "",required=true) @PathVariable("email") String email) {
        try {
            this.userService.changeBlockedStatus(email, true);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Utilisateur d'identifiant " + email + " est maintenant bloqué", HttpStatus.OK);
    }

    public ResponseEntity<String> changePassword(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangePassword changePassword, Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();
        try {
            this.userService.changePassword(
                    userDetails.getEmail(),
                    changePassword.getOldPassword(),
                    changePassword.getNewPassword()
            );
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Mot de passe changé", HttpStatus.OK);
    }

    public ResponseEntity<String> checkCode(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CodeCheck codeCheck) {
        if (this.userService.checkCode(codeCheck.getEmail(), codeCheck.getCode()))
            return new ResponseEntity<>("La vérification a réussie", HttpStatus.OK);
        return new ResponseEntity<>("La vérification a échoué", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> create(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Utilisateur à créer" ,required=true )  @Valid @RequestBody User user) {
        try {
            return new ResponseEntity<>(this.userService.create(user), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Email de l'utilisateur à supprimer",required=true) @PathVariable("email") String email, Principal principal) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        try {
            this.userService.checkOwner(userDetails.getEmail(), email);
            this.userService.delete(email);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UnauthorizedAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Utilisateur (id=" + email + ") supprimé", HttpStatus.OK);
    }

    public ResponseEntity<String> forgotPassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserId userId) {
        try {
            this.userService.forgotPassword(userId.getUserId(), request);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Un mail vous a été envoyé pour réinitialiser votre mot de passe",
                HttpStatus.OK);
    }

    public ResponseEntity<?> get(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Email de l'utilisateur à récupérer",required=true) @PathVariable("email") String email, Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        User user = null;
        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                user = this.userService.get(email);
            }
        } catch (UnauthorizedAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<Page<User>> getAll(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization, Pageable pgble, Principal principal) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        if (this.userService.isAdmin(userDetails.getEmail())) {
            return new ResponseEntity<>(this.userService.get(pgble), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(this.userService.get(userDetails.getEmail(), pgble), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> getCode(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        this.userService.getCode(email);
        return new ResponseEntity<>("Email a été envoyée à cette adresse, si elle existe vraiment, avec le code", HttpStatus.OK);
    }

    public ResponseEntity<String> resetPassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ResetPassword resetPassword) {
        try {
            this.userService.resetPassword(resetPassword.getToken(), resetPassword.getNewPassword());
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Un mail a été envoyé avec un lien de réinitialisation", HttpStatus.OK);
    }

    public ResponseEntity<String> resetPasswordPage(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "token", required = true) String token) {
        String uid = this.tokenService.getUidFromJWT(token);
        if (this.tokenService.validateToken(token)) {
            return new ResponseEntity<>("Page de connexion", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Le token n'est plus valide", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> unblock(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "",required=true) @PathVariable("email") String email) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<String>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> update(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "email de l'utilisateur à mettre à jour",required=true) @PathVariable("email") String email,@ApiParam(value = "Utilisateur à mettre à jour" ,required=true )  @Valid @RequestBody User user, Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                this.userService.update(email, user);
            }
        } catch (UnauthorizedAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Utilisateur (id=" + email + ") mise à jour", HttpStatus.OK);
    }

}
