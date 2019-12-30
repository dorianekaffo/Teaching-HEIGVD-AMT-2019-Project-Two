package ch.heigvd.p2.firstapi.controller;

import ch.heigvd.p2.firstapi.dto.ChangePassword;
import ch.heigvd.p2.firstapi.dto.CodeCheck;
import ch.heigvd.p2.firstapi.dto.ResetPassword;
import ch.heigvd.p2.firstapi.dto.UserId;
import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.exception.UnauthorizedAccessException;
import ch.heigvd.p2.firstapi.model.User;
import ch.heigvd.p2.firstapi.repository.IRoleRepository;
import ch.heigvd.p2.firstapi.repository.IUserRepository;
import ch.heigvd.p2.firstapi.security.CustomUserDetails;
import ch.heigvd.p2.firstapi.security.TokenService;
import ch.heigvd.p2.firstapi.service.RoleService;
import ch.heigvd.p2.firstapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.create(user), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<User>> getAll(Principal principal, Pageable pgble) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        if (this.roleService.isAdmin(userDetails.getEmail())) {
            return new ResponseEntity<>(this.userService.get(pgble), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(this.userService.get(userDetails.getEmail(), pgble), HttpStatus.OK);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> get(
            Principal principal,
            @PathVariable(name = "email") String email) throws RessourceNotFoundException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                return new ResponseEntity<>(this.userService.get(email), HttpStatus.OK);
            }
        } catch (UnauthorizedAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return null;

    }

    @PutMapping("/{email}")
    public ResponseEntity<?> update(
            Principal principal,
            @PathVariable(name = "email") String email,
            @RequestBody User user
    ) throws RessourceNotFoundException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                return new ResponseEntity<>(this.userService.update(email, user), HttpStatus.OK);
            }
        } catch (UnauthorizedAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return null;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(
            Principal principal,
            @PathVariable(name = "email") String email
    ) throws RessourceNotFoundException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();

        try {
            if (this.userService.checkOwner(userDetails.getEmail(), email)) {
                this.userService.delete(email);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (UnauthorizedAccessException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/blocked/{email}")
    public ResponseEntity<?> blockUser(
            @PathVariable(name = "email") String email
    ) throws RessourceNotFoundException {
        this.userService.changeBlockedStatus(email, true);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/blocked/{email}")
    public ResponseEntity<?> unblockUser(
            @PathVariable(name = "email") String email
    ) throws RessourceNotFoundException {
        this.userService.changeBlockedStatus(email, false);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody UserId userID,
            HttpServletRequest request
    ) throws RessourceNotFoundException {
        this.userService.forgotPassword(userID.getEmail(), request);
        return new ResponseEntity<>("Un mail vous a été envoyé pour réinitialiser votre mot de passe",
                HttpStatus.OK);
    }

    @GetMapping("/password")
    public ResponseEntity<?> initResetPassword(
            @RequestParam(name = "token") String token
    ) throws RessourceNotFoundException {
        String uid = this.tokenService.getUidFromJWT(token);
        if (this.tokenService.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Le token n'est plus valide", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPassword resetPassword
            ) throws RessourceNotFoundException {
        this.userService.resetPassword(resetPassword.getToken(), resetPassword.getNewPassword());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(
            Principal principal,
            @RequestBody ChangePassword changePassword
    ) throws RessourceNotFoundException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        CustomUserDetails userDetails = (CustomUserDetails)token.getPrincipal();
        this.userService.changePassword(
                userDetails.getEmail(),
                changePassword.getOldPassword(),
                changePassword.getNewPassword()
        );
        return new ResponseEntity<>("Mot de passe changé", HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<?> getCode(
            @RequestParam(name="email") String email
    ) {
        this.userService.getCode(email);
        return new ResponseEntity<>("Email a été envoyée à cette adresse, si elle existe vraiment, avec le code", HttpStatus.OK);
    }

    @PostMapping("/code")
    public ResponseEntity<String> checkCode(
            @RequestBody CodeCheck codeCheck
    ) {
        if (this.userService.checkCode(codeCheck.getEmail(), codeCheck.getCode()))
            return new ResponseEntity<>("La vérification a réussie", HttpStatus.OK);
        return new ResponseEntity<>("La vérification a échoué", HttpStatus.BAD_REQUEST);
    }

}
