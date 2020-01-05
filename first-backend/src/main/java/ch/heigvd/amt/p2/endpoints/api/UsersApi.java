/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ch.heigvd.amt.p2.endpoints.api;

import ch.heigvd.amt.p2.endpoints.model.ChangePassword;
import ch.heigvd.amt.p2.endpoints.model.CodeCheck;
import ch.heigvd.amt.p2.endpoints.model.ResetPassword;
import ch.heigvd.amt.p2.endpoints.model.UserId;
import ch.heigvd.amt.p2.model.User;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.security.Principal;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-01T15:06:39.162Z")

@Api(value = "users", description = "the users API")
public interface UsersApi {

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Bloque l'utilisateur ayant l'email correspondant", nickname = "block", notes = "Bloque l'utilisateur ayant l'`email` correspondant", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Utilisateur bloqué") })
    @RequestMapping(value = "/users/block/{email}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<?> block(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "",required=true) @PathVariable("email") String email);

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Change le mot de passe de l'utilisateur", nickname = "changePassword", notes = "Change le mot de passe de l'utilisateur", response = String.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Opération réussie", response = String.class) })
    @RequestMapping(value = "/users/password",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<String> changePassword(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "" ,required=true )  @Valid @RequestBody ChangePassword changePassword, Principal principal);


    @ApiOperation(value = "Vérification du code", nickname = "checkCode", notes = "Vérifie le code qui a été envoyé par mail", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Feedback sur la vérification du code") })
    @RequestMapping(value = "/users/code",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<String> checkCode(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CodeCheck codeCheck);

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Créer un utilisateur", nickname = "create", notes = "Crée un nouvel utilisateur", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Opération réussie", response = User.class) })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<?> create(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Utilisateur à créer" ,required=true )  @Valid @RequestBody User body);


    @ApiOperation(value = "Supprime un utilisateur", nickname = "delete", notes = "Supprime un utilisateur avec `email` comme adresse email. Cette action ne peut qu'être effectué par l'administrateur", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Email invalide"),
        @ApiResponse(code = 404, message = "Utilisateur non trouvée") })
    @RequestMapping(value = "/users/{email}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Email de l'utilisateur à supprimer",required=true) @PathVariable("email") String email, Principal principal);


    @ApiOperation(value = "Envoi le mail pour la réinitialisation du mot de passe", nickname = "forgotPassword", notes = "Envoi le mail de réinitialisation", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Mail de réinitialisation envoyé") })
    @RequestMapping(value = "/users/reset-password",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<String> forgotPassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserId userId);


    @ApiOperation(value = "Récupère un utilisateur avec l'email correspondant", nickname = "get", notes = "Récupère un utilisateur avec l'`email` correspondant", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Opération réussie", response = User.class),
        @ApiResponse(code = 400, message = "Email invalide"),
        @ApiResponse(code = 404, message = "Utilisateur non trouvée") })
    @RequestMapping(value = "/users/{email}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> get(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Email de l'utilisateur à récupérer",required=true) @PathVariable("email") String email, Principal principal);


    @ApiOperation(value = "Liste de tous les utilisateurs", nickname = "getAll", notes = "Liste complète de tous les utilisateurs", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Tous les utilisateurs") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Page<User>> getAll(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization, Pageable pgble, Principal principal);


    @ApiOperation(value = "Envoi un code vers à l'adresse mail renseigné", nickname = "getCode", notes = "Envoi un code vers l'adresse mail correspondant", response = String.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Code envoyé à l'email", response = String.class) })
    @RequestMapping(value = "/users/code",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getCode(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email);


    @ApiOperation(value = "Attribut un nouveau mot de passe à l'utilisateur", nickname = "resetPassword", notes = "Attribut un nouveau mot de passe suite à une réinitialisation", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Mot de passe changé") })
    @RequestMapping(value = "/users/password",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<String> resetPassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ResetPassword resetPassword);


    @ApiOperation(value = "Ouvre la page pour le changement de mot de passe", nickname = "resetPasswordPage", notes = "Ouvre la page pour le changement du mot de passe", response = String.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Page pour réinitialisation du mot de passe", response = String.class) })
    @RequestMapping(value = "/users/reset-password",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> resetPasswordPage(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "token", required = true) String token);

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Débloque l'utilisateur ayant l'email correspondant", nickname = "unblock", notes = "Débloque l'utilisateur ayant l'email correspondant", response = String.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Utilisateur débloqué", response = String.class) })
    @RequestMapping(value = "/users/block/{email}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<String> unblock(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "",required=true) @PathVariable("email") String email);


    @ApiOperation(value = "Mettre à jour un utilisateur", nickname = "update", notes = "Mets à jour l'utilisateur avec l'`email` correspondant. Ne peut qu'être fait par un utilisateur connecté", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Email invalide"),
        @ApiResponse(code = 404, message = "Utilisateur non retrouvé") })
    @RequestMapping(value = "/users/{email}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<?> update(@ApiParam(value = "Contient le token d'authentification sous la forme `Bearer {token}`" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "email de l'utilisateur à mettre à jour",required=true) @PathVariable("email") String email,@ApiParam(value = "Utilisateur à mettre à jour" ,required=true )  @Valid @RequestBody User user, Principal principal);

}
