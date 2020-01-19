package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import ch.heigvd.amt.p2.api.AuthApi;
import ch.heigvd.amt.p2.api.UserApi;
import ch.heigvd.amt.p2.dto.ChangePassword;
import ch.heigvd.amt.p2.dto.Credentials;
import ch.heigvd.amt.p2.dto.UserDto;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Stepdefs {

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private Object body;

    private String token;
    private String email;
    private String password;
    private String newPassword;

    private UserApi usersApi = new UserApi();
    private AuthApi authApi = new AuthApi();

    private UserDto user;
    private Credentials credentials;

    // Initialisation du serveur
    @Given("^J'ai un serveur$")
    public void jAiUnServeur() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.p2.first-server.url");

        authApi.getApiClient().setHttpClient(authApi.getApiClient().getHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS).build());
        usersApi.getApiClient().setHttpClient(usersApi.getApiClient().getHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS).build());

        authApi.getApiClient().setBasePath(url);
        usersApi.getApiClient().setBasePath(url);
    }

    @Then("^Je reçois une réponse de code (\\d+)$")
    public void jeReçoisUneRéponseDeCode(int arg0) {
        assertEquals(arg0, lastStatusCode);
    }

    @Given("^J'ai l'adresse email \"([^\"]*)\" et le mot de passe \"([^\"]*)\"$")
    public void jAiLAdresseEmailEtLeMotDePasse(String email, String password) throws Throwable {
        credentials = new Credentials();
        credentials.setEmail(email);
        credentials.setPassword(password);
    }

    @When("^Je fais un POST vers le chemin /auth/login$")
    public void jeFaisUnPOSTVersLeCheminAuthLogin() {
        try {
            lastApiResponse = authApi.loginWithHttpInfo(credentials);
            lastApiCallThrewException = false;
            lastApiException = null;
            body = lastApiResponse.getData();
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            body = e.getResponseBody();
            lastStatusCode = e.getCode();
        }
    }

    @And("^Je reçois un token$")
    public void jeReçoisUnToken() {
        assertNotNull(body);
        assertTrue(body instanceof String);
        this.token = (String)body;
    }

    @And("^Je reçois une le message \"([^\"]*)\"$")
    public void jeReçoisUneLeMessage(String message) throws Throwable {
        assertEquals(message, body);
    }

    @Given("^J'ai un utilisateur d'identifiant \"([^\"]*)\"$")
    public void jAiUnUtilisateurDIdentifiant(String arg0) throws Throwable {
        this.email = arg0;
    }

    @When("^Je fais un POST vers le chemin /users$")
    public void jeFaisUnPOSTVersLeCheminUsers() {
        try {
            usersApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = usersApi.createWithHttpInfo(user);
            lastApiCallThrewException = false;
            body = lastApiResponse.getData();
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            body = e.getResponseBody();
            lastStatusCode = e.getCode();
        }
    }

    @Given("^Je veux créer un utilisateur d'identifiant \"([^\"]*)\", de mot de passe \"([^\"]*)\", de prénom \"([^\"]*)\", de nom \"([^\"]*)\" et de role \"([^\"]*)\"$")
    public void jeVeuxCréerUnUtilisateurDIdentifiantDeMotDePasseDePrénomEtDeNom(String email, String password, String firstname, String lastname, String role) throws Throwable {
        user = new UserDto();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setRole(role);
    }

    @When("^Je fais un PUT vers le chemin /users/block/ avec email$")
    public void jeFaisUnPUTVersLeCheminUsersBlockEmail() {
        try {
            usersApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = usersApi.blockWithHttpInfo(this.email);
            lastApiCallThrewException = false;
            body = lastApiResponse.getData();
            lastStatusCode = lastApiResponse.getStatusCode();
            System.out.println("Code d'exécution: " + lastStatusCode);
            System.out.println("Corps: " + body);
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            body = e.getResponseBody();
            lastStatusCode = e.getCode();
            System.out.println("Code d'exécution: " + e.getCode());
        }
    }

    @When("^Je fais un DELETE vers le chemin /users/block/ avec email$")
    public void jeFaisUnDELETEVersLeCheminUsersBlockEmail() {
        try {
            usersApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = usersApi.unblockWithHttpInfo(this.email);
            lastApiCallThrewException = false;
            body = lastApiResponse.getData();
            lastStatusCode = lastApiResponse.getStatusCode();
            System.out.println("Code d'exécution: " + lastStatusCode);
            System.out.println("Corps: " + body);
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            body = e.getResponseBody();
            lastStatusCode = e.getCode();
            System.out.println("Code d'exécution: " + lastStatusCode);
        }
    }

    @Given("^Je veux changer mon mot de passe \"([^\"]*)\" en \"([^\"]*)\"$")
    public void jeVeuxChangerMonMotDePasseEn(String arg0, String arg1) throws Throwable {
        this.password = arg0;
        this.newPassword = arg1;
    }

    @And("^Je fais un PUT vers le chemin /users/password$")
    public void jeFaisUnPUTVersLeCheminUsersPassword() {
        try {
            usersApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            ChangePassword changePassword = new ChangePassword();
            changePassword.setNewPassword(this.newPassword);
            changePassword.setOldPassword(this.password);
            lastApiResponse = usersApi.changePasswordWithHttpInfo(changePassword);
            lastApiCallThrewException = false;
            body = lastApiResponse.getData();
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            body = e.getResponseBody();
            lastStatusCode = e.getCode();
        }
    }

    @And("^Je veux son mot de passe en \"([^\"]*)\"$")
    public void jeVeuxSonMotDePasseEn(String arg0) throws Throwable {
        this.newPassword = arg0;
    }

}
