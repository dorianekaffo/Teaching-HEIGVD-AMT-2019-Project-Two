package ch.heigvd.amt.p2.api;

import ch.heigvd.amt.p2.api.dto.ChangePassword;
import ch.heigvd.amt.p2.api.dto.CodeCheck;
import ch.heigvd.amt.p2.api.dto.ResetPassword;
import ch.heigvd.amt.p2.api.dto.UserDto;
import ch.heigvd.amt.p2.api.dto.UserId;
import ch.heigvd.amt.p2.exception.PasswordMismatchException;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.ForbiddenAccessException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-11T19:41:15.507Z")

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
    private ITemplateEngine templateEngine;

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<String> block(@ApiParam(value = "",required=true) @PathVariable("email") String email) {
        String accept = request.getHeader("Accept");
        try {
            this.userService.toggleBlock(email, true);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Utilisateur (id=" + email + ") est maintenant bloqué", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangePassword changePassword) {

        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        try {
            this.userService.changePassword(
                    userDetails.getEmail(),
                    changePassword.getOldPassword(),
                    changePassword.getNewPassword()
            );
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PasswordMismatchException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Mot de passe changé", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> checkCode(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CodeCheck codeCheck) {
        try {
            if (this.userService.checkCode(codeCheck.getEmail(), codeCheck.getCode()))
                return new ResponseEntity<>("La vérification a réussie", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("La vérification a échoué", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("La vérification a échoué", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<UserDto> create(@ApiParam(value = "Utilisateur à créer" ,required=true )  @Valid @RequestBody UserDto body) {

        User user = this.userService.convertToEntity(body);
        user = this.userService.create(user);
        return new ResponseEntity<>(this.userService.convertToDto(user), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<String> forgotPassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserId userId) {

        try {
            this.userService.forgotPassword(userId.getUserId(), request);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Un mail vous a été envoyé pour réinitialiser votre mot de passe",
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> resetPassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ResetPassword resetPassword) {
        try {
            this.userService.resetPassword(resetPassword.getToken(), resetPassword.getNewPassword());
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Mot de passe réinitialiser", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> resetPasswordPage(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "token", required = true) String token) {
        String uid = this.tokenService.getUidFromJWT(token);
        if (this.tokenService.validateToken(token)) {
            Context context = new Context();
            context.setVariable("token", token);
            String pageConnexion = this.templateEngine.process("reset_password", context);
            return new ResponseEntity<>(pageConnexion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Le token n'est plus valide", HttpStatus.UNAUTHORIZED);
        }
    }


    @Override
    public ResponseEntity<String> sendCode(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        try {
            this.userService.sendCode(email);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Aucun utilisateur ayant cette adresse mail", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Un code a été envoyé à l'adresse email fournit", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<String> unblock(@ApiParam(value = "",required=true) @PathVariable("email") String email) {

        try {
            this.userService.toggleBlock(email, false);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Utilisateur (id=" + email + ") débloqué", HttpStatus.OK);

    }

}
