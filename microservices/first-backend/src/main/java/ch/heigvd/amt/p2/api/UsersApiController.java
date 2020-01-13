package ch.heigvd.amt.p2.api;

import ch.heigvd.amt.p2.api.dto.ChangePassword;
import ch.heigvd.amt.p2.api.dto.CodeCheck;
import ch.heigvd.amt.p2.api.dto.PagedResponse;
import ch.heigvd.amt.p2.api.dto.ResetPassword;
import ch.heigvd.amt.p2.api.dto.UserDTO;
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
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<String> block(@ApiParam(value = "",required=true) @PathVariable("email") String email) {
        String accept = request.getHeader("Accept");
        try {
            this.userService.changeBlockedStatus(email, true);
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
        if (this.userService.checkCode(codeCheck.getEmail(), codeCheck.getCode()))
            return new ResponseEntity<>("La vérification a réussie", HttpStatus.OK);
        return new ResponseEntity<>("La vérification a échoué", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<UserDTO> create(@ApiParam(value = "Utilisateur à créer" ,required=true )  @Valid @RequestBody UserDTO body) {

        User user = this.userService.convertToEntity(body);
        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        user.setOwner(userDetails.getEmail());
        user = this.userService.create(user);
        return new ResponseEntity<>(this.userService.convertToDto(user), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> delete(@ApiParam(value = "Email de l'utilisateur à supprimer",required=true) @PathVariable("email") String email) {

        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        try {
            this.userService.checkOwner(userDetails.getEmail(), email);
            this.userService.delete(email);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Utilisateur (id=" + email + ") supprimé", HttpStatus.OK);
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
    public ResponseEntity<UserDTO> get(@ApiParam(value = "Email de l'utilisateur à récupérer",required=true) @PathVariable("email") String email) {

        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = null;
        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                user = this.userService.get(email);
            }
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.userService.convertToDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedResponse> getAll(@ApiParam(value = "", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,@ApiParam(value = "", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize) {

        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (this.userService.isAdmin(userDetails.getEmail())) {
            Page users = this.userService.get(PageRequest.of(page, pageSize));

            // Initialisation de page
            PagedResponse response = new PagedResponse();
            response.setContent(users.getContent());
            response.setPageNumber(users.getNumber());
            response.setPageSize(users.getSize());
            response.setTotalPages(users.getTotalPages());
            response.setTotalElements(users.getNumberOfElements());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            Page users = this.userService.get(userDetails.getEmail(), PageRequest.of(page, pageSize));

            // Initialisation de page
            PagedResponse response = new PagedResponse();
            response.setContent(users.getContent());
            response.setPageNumber(users.getNumber());
            response.setPageSize(users.getSize());
            response.setTotalPages(users.getTotalPages());
            response.setTotalElements(users.getNumberOfElements());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
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
            return new ResponseEntity<>("Page de connexion", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Le token n'est plus valide", HttpStatus.UNAUTHORIZED);
        }
    }


    @Override
    public ResponseEntity<String> sendCode(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        this.userService.getCode(email);
        return new ResponseEntity<>("Un code a été envoyé à l'adresse email fournit", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<String> unblock(@ApiParam(value = "",required=true) @PathVariable("email") String email) {

        try {
            this.userService.changeBlockedStatus(email, false);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Utilisateur (id=" + email + ") débloqué", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserDTO> update(@ApiParam(value = "email de l'utilisateur à mettre à jour",required=true) @PathVariable("email") String email,@ApiParam(value = "Utilisateur à mettre à jour" ,required=true )  @Valid @RequestBody UserDTO body) {

        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = this.userService.convertToEntity(body);
        user.setOwner(userDetails.getEmail());

        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                user = this.userService.update(email, user);
            }
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.userService.convertToDto(user), HttpStatus.OK);
    }

}
