package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import ch.heigvd.amt.p2.api.AuthApi;
import ch.heigvd.amt.p2.dto.Credentials;
import ch.heigvd.amt.p2.dto.UserDTO;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class AuthStepdefs {

    private String email;
    private String password;

    private AuthApi authApi;
    private Credentials credentials;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;

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
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je reçois un token$")
    public void jeReçoisUnToken() {
        assertNotNull(lastApiResponse.getData());
        assertTrue(lastApiResponse.getData() instanceof String);
    }

    @And("^Je reçois une le message \"([^\"]*)\"$")
    public void jeReçoisUneLeMessage(String message) throws Throwable {
        assertEquals(lastApiResponse.getData(), message);
    }

}
