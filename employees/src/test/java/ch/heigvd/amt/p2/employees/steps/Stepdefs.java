package ch.heigvd.amt.p2.employees.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.*;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Stepdefs {

    int departmentId;
    String departmentName;

    int employeeId;
    String employeeName;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    Response response;
    OkHttpClient client = new OkHttpClient();
    String token;
    String body;

    @Given("Je veux créer un departement d'identifiant {int} et de nom {string}")
    public void jeVeuxCréerUnDepartementDIdentifiantEtDeNom(int arg0, String arg1) {
        this.departmentId = arg0;
        this.departmentName = arg1;
    }

    @When("Je fais un POST vers le chemin {string}")
    public void jeFaisUnPOSTVersLeChemin(String arg0) throws IOException {
        RequestBody body = RequestBody.create(JSON, this.getBody(arg0));
        Request request = new Request.Builder()
                .url("http://localhost:8080" + arg0)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
        }
    }

    @Then("Je reçois une réponse de code {int}")
    public void jeReçoisUneRéponseDeCode(int arg0) {
        assertTrue(this.response.code() == arg0);
    }

    @When("Je fais un GET vers le chemin {string}")
    public void jeFaisUnGETVersLeChemin(String arg0) throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080" + arg0)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
        }
    }

    @Given("J{string}identifiant {int} d'un département")
    public void jAiLIdentifiantDUnDépartement(int arg0) {
        this.departmentId = arg0;
    }

    @When("Je fais un PUT vers le chemin {string}")
    public void jeFaisUnPUTVersLeChemin(String arg0) throws IOException {
        RequestBody body = RequestBody.create(JSON, this.getBody(arg0));
        Request request = new Request.Builder()
                .url("http:/localhost:8080" + arg0)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
        }
    }

    @When("Je fais un DELETE vers le chemin {string}")
    public void jeFaisUnDELETEVersLeChemin(String arg0) throws IOException {
        RequestBody body = RequestBody.create(JSON, this.getBody(arg0));
        Request request = new Request.Builder()
                .url("http://localhost:8080" + arg0)
                .delete(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
        }
    }

    @Given("Je veux créer un employé d'identifiant {int} et de nom {string}")
    public void jeVeuxCréerUnEmployéDIdentifiantEtDeNom(int arg0, String arg1) {
        this.employeeId = arg0;
        this.employeeName = arg1;
    }

    @Given("J{string}identifiant {int} d'un employé")
    public void jAiLIdentifiantDUnEmployé(int arg0) {
        this.employeeId = arg0;
    }

    @Given("J{string}identifiant {int}")
    public void jAiUnEmployéDIdentifiant(int arg0) {
    }

    @And("Un département d'identifiant {int}")
    public void unDépartementDIdentifiant(int arg0) {
    }

    private String getBody(String path) {
        if (path.startsWith("/employees")) {
            return "{\"id\": \"" + this.employeeId + "\", \"name\": \"" + this.employeeName + "\"}";
        } else if (path.startsWith("/departments")) {
            return "{\"id\": \"" + this.departmentId + "\", \"name\": \"" + this.departmentName + "\"}";
        }
        return "";
    }
}
