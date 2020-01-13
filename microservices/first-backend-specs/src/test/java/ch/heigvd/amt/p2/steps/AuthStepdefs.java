package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import ch.heigvd.amt.p2.api.AuthApi;
import ch.heigvd.amt.p2.dto.Credentials;
import ch.heigvd.amt.p2.dto.UserDTO;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class AuthStepdefs {

    private String email;
    private String password;

    private AuthApi authApi = new AuthApi();
    private Credentials credentials;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private Object body;

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

    @Given("^J'ai un serveur$")
    public void jAiUnServeur() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.p2.first-server.url");
        authApi.getApiClient().setBasePath(url);
    }
}
