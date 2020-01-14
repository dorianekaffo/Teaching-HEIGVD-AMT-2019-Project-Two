package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import ch.heigvd.amt.p2.api.UserApi;
import ch.heigvd.amt.p2.dto.PagedResponse;
import ch.heigvd.amt.p2.dto.UserDTO;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.Properties;
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


    private UserApi usersApi = new UsersApi();
    private AuthApi authApi = new AuthApi();

    private UserDTO user;
    private Credentials credentials;

    // Initialisation du serveur
    @Given("^J'ai un serveur$")
    public void jAiUnServeur() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.p2.first-server.url");
        authApi.getApiClient().setBasePath(url);
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
            System.out.println("Code de status: " + lastApiResponse.getStatusCode());
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @And("^Je reçois un token$")
    public void jeReçoisUnToken() {
        assertNotNull(body);
        assertTrue(body instanceof String);
    }

    @And("^Je reçois une le message \"([^\"]*)\"$")
    public void jeReçoisUneLeMessage(String message) throws Throwable {
        assertEquals(message, body);
    }

    @When("^Je fais un POST vers le chemin \"([^\"]*)\"$")
    public void jeFaisUnPOSTVersLeChemin(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Je suis connecté comme admin$")
    public void jeSuisConnectéCommeAdmin() {
    }

    @Given("^Je veux créer un utilisateur d'identifiant \"([^\"]*)\" et mot de passe \"([^\"]*)\"$")
    public void jeVeuxCréerUnUtilisateurDIdentifiantEtMotDePasse(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

//    @Then("^Je reçois une réponse de code (\\d+)$")
//    public void jeReçoisUneRéponseDeCode(int arg0) {
//
//    }

    @Given("^J'ai un utilisateur d'identifiant \"([^\"]*)\"$")
    public void jAiUnUtilisateurDIdentifiant(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Je fais un POST vers le chemin /users$")
    public void jeFaisUnPOSTVersLeCheminUsers() {
        try {
            lastApiResponse = usersApi.createWithHttpInfo(user);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Given("^Je veux créer un utilisateur d'identifiant \"([^\"]*)\", de mot de passe \"([^\"]*)\", de prénom \"([^\"]*)\" et de nom \"([^\"]*)\"$")
    public void jeVeuxCréerUnUtilisateurDIdentifiantDeMotDePasseDePrénomEtDeNom(String email, String password, String firstname, String lastname) throws Throwable {
        user = new UserDTO();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
    }

    @Then("^L'utilisateur est présent$")
    public void lUtilisateurEstPrésent() {
        assertTrue(lastApiResponse.getData() instanceof PagedResponse);
        PagedResponse response = (PagedResponse) lastApiResponse.getData();
        assertTrue(response.getContent().stream()
                .filter((element) -> ((UserDTO)element).getEmail().equals(user.getEmail())
                ).collect(Collectors.toList()).size() == 1);
    }

    @And("^Je fais un GET vers le chemin /users\\?page=(\\d+)&size=(\\d+)$")
    public void jeFaisUnGETVersLeCheminUsersPageSize(int pageNumber, int pageSize) {
        try {
            lastApiResponse = usersApi.getAllWithHttpInfo(pageNumber, pageSize);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un PUT vers le chemin /users/block/ avec email$")
    public void jeFaisUnPUTVersLeCheminUsersBlockEmail() {
        try {
            lastApiResponse = usersApi.blockWithHttpInfo(this.email);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un DELETE vers le chemin /users/block/ avec email$")
    public void jeFaisUnDELETEVersLeCheminUsersBlockEmail() {
        try {
            lastApiResponse = usersApi.unblockWithHttpInfo(this.email);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
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
            lastApiResponse = usersApi.blockWithHttpInfo(this.email);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je veux son mot de passe en \"([^\"]*)\"$")
    public void jeVeuxSonMotDePasseEn(String arg0) throws Throwable {
        this.newPassword = arg0;
    }

}
