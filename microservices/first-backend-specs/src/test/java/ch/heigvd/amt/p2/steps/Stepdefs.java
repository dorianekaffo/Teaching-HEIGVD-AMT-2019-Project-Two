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

import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class Stepdefs {

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;

    private String token;
    private String email;

    private UserApi usersApi;
    private UserDTO user;

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

    @Then("^Je reçois une réponse de code (\\d+)$")
    public void jeReçoisUneRéponseDeCode(int arg0) {
    }

    @Given("^J'ai un utilisateur d'identifiant \"([^\"]*)\"$")
    public void jAiUnUtilisateurDIdentifiant(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Je fais un PUT vers le chemin \"([^\"]*)\"$")
    public void jeFaisUnPUTVersLeChemin(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Je fais un DELETE vers le chemin \"([^\"]*)\"$")
    public void jeFaisUnDELETEVersLeChemin(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^J'obtiens un token d'authentification$")
    public void jObtiensUnTokenDAuthentification() {
        this.token = (String)lastApiResponse.getData();
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
}
