package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;


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
}
