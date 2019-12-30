package ch.heigvd.amt.p2.users.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Stepdefs {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    String userPayload;

    String email;
    String firstname;
    String lastname;
    String password;

    OkHttpClient client = new OkHttpClient();
    Response response;
    String token;
    String body;

    String newPassword;
    String oldPassword;

    @Given("Je suis connecté")
    public void jeSuisConnecté() {
    }

    @And("J{string}identifiant {string} dont je suis le propriétaire")
    public void jAiUnUtilisateurDIdentifiantDontJeSuisLePropriétaire(String arg0) {
    }

    @And("J{string}identifiant {string} dont je ne suis pas le propriétaire")
    public void jAiUnUtilisateurDIdentifiantDontJeNeSuisPasLePropriétaire(String arg0) {
    }

    @When("Je fais un GET vers le chemin {string}")
    public void jeFaisUnGETVersLeChemin(String arg0) throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/auth/login")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
        }
    }

    @Then("Je reçois une réponse de code {int}")
    public void jeReçoisUneRéponseDeCode(int arg0) {
        assertEquals(this.response.code(), arg0);
    }

    @And("L'objet sous forme paginée des utilisateurs")
    public void lObjetSousFormePaginéeDesUtilisateurs() {
    }

    @Given("J{string}identifiant d'un utilisateur {string}")
    public void jAiLIdentifiantDUnUtilisateur(String arg0) {
        this.email = arg0;
    }

    @When("Je fais un PUT vers le chemin {string}")
    public void jeFaisUnPUTVersLeChemin(String arg0) throws IOException {        RequestBody body = RequestBody.create(JSON, "{\"email\": \"" + this.email +"\", \"password\": \""+ this.password + "\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080" + arg0)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.token = response.body().string();
        }
    }

    @Given("J{string}identifiant {string}")
    public void jAiLIdentifiant(String arg0) {
        this.email = arg0;
    }

    @When("Je fais un DELETE vers le chemin {string}")
    public void jeFaisUnDELETEVersLeChemin(String arg0) throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"email\": \"" + this.email +"\", \"password\": \""+ this.password + "\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080" + arg0)
                .delete(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.token = response.body().string();
        }
    }

    @Given("Je veux changer mon mot de passe {string} en {string}")
    public void jeVeuxChangerMonMotDePasseEn(String arg0, String arg1) {
        this.oldPassword = arg0;
        this.newPassword = arg1;
    }

    @Then("Je recois une réponse de code {int}")
    public void jeRecoisUneRéponseDeCode(int arg0) {
        assertEquals(this.response.code(), arg0);
    }

    @Given("J{string}ai l{string}identifant {string} d'un utilisateur")
    public void jAiLIdentifantDUnUtilisateur(String arg0) {
        this.email = arg0;
    }

    @When("Je fais un POST vers le chemin {string}")
    public void jeFaisUnPOSTVersLeChemin(String arg0) throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"email\": \"" + this.email +"\", \"password\": \""+ this.password + "\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080" + arg0)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.body = response.body().string();
        }
    }

    @Given("J{string}identifiant {string} et mot de passe {string}")
    public void jAiUnUtilisateurDIdentifiantEtMotDePasse(String arg0, String arg1) {
        this.email = arg0;
        this.password = arg1;
    }

    @Given("J{string}adresse email {string} et le mot de passe {string}")
    public void jAiLAdresseEmailEtLeMotDePasse(String arg0, String arg1) {
        this.email = arg0;
        this.password = arg1;
    }

    @And("Un token d'authentification")
    public void unTokenDAuthentification() { assertFalse(this.token.isEmpty());
    }

    @Given("Je suis connecté comme {string}")
    public void jeSuisConnectéComme(String arg0) {
    }

    @Given("Je suis connecté comme admin")
    public void jeSuisConnectéCommeAdmin() throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"email\": \"dorianekaffo@gmail.com\", \"password\": \"administrator\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/auth/login")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.token = response.body().string();
        }
    }

    @Given("Je veux créer un utilisateur d'identifiant {string} et mot de passe {string}")
    public void jeVeuxCréerUnUtilisateurDIdentifiantEtMotDePasse(String arg0, String arg1) {
        this.email = arg0;
        this.password = arg1;
    }

    private String getBody() {
        Map<String, String> body = new HashMap<>();
        if (!this.email.isEmpty()) body.put("email", this.email);
        if (!this.firstname.isEmpty()) body.put("firstname", this.firstname);
        if (!this.lastname.isEmpty()) body.put("lastname", this.lastname);
        if (!this.password.isEmpty()) body.put("email", this.password);

        return body.toString();

    }
}
