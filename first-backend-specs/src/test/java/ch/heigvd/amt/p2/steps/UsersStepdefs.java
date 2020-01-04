package ch.heigvd.amt.p2.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.*;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class UsersStepdefs {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    String userPayload;
    String email;
    String password;
    OkHttpClient client = new OkHttpClient();
    Response response;
    String token;
    String body;

    @Given("^I am logged$")
    public void iAmLogged() throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"email\": \"dorianekaffo@gmail.com\", \"password\": \"administrator\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/auth/login")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.token = response.body().string();
        }

        this.email = "olivierliechti@heigvd.com";

    }

    @Given("I have a User payload")
    public void iHaveAUserPayload() {
        this.userPayload = "{\"email\": \"olivierliechti@heigvd.ch\", \"firstname\": \"Olivier\", \"lastname\": \"Liechti\", \"password\": \"Liechti\"}";
    }

    @When("I POST it to the \\/users endpoint")
    public void iPOSTItToTheUsersEndpoint() throws IOException {
        System.out.println("Voici le token: " + this.token);
        RequestBody body = RequestBody.create(JSON, this.userPayload);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.token)
                .url("http://localhost:8080/users")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.body = response.body().string();
        }
    }

    @Given("I have a the email of a user")
    public void iHaveATheEmailOfAUser() {
        this.email = "olivierliechti@heigvd.com";
    }

    @When("I GET it to the \\/users\\/{string} endpoint")
    public void iGETItToTheUsersEndpoint(String arg0) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.token)
                .url("http://localhost:8080/users/" + this.email)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.body = response.body().string();
        }
    }

    @Then("I receive a {int} status code and a user payload")
    public void iReceiveAStatusCodeAndAUserPayload(int arg0) {
        System.out.println("Voici la reponse: " + this.response);
        assertTrue(this.response.code() == arg0);
    }

    @When("I PUT it to the \\/users endpoint")
    public void iPUTItToTheUsersEndpoint() throws IOException {
        System.out.println("Voici le token: " + this.token);
        RequestBody body = RequestBody.create(JSON, this.userPayload);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.token)
                .url("http://localhost:8080/users/" + this.email)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.body = response.body().string();
        }
    }

    @When("I DELETE it to the \\/users\\/{string} endpoint")
    public void iDELETEItToTheUsersEndpoint(String arg0) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.token)
                .url("http://localhost:8080/users/" + this.email)
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.body = response.body().string();
        }
    }

    @Then("I receive a {int} status code with a feedback")
    public void iReceiveAStatusCodeWithAFeedback(int arg0) {
        System.out.println("Voici la reponse: " + this.response);
        assertTrue(this.response.code() == arg0);
    }

    @Then("^uI receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int arg0) {
        System.out.println("Voici la reponse: " + this.response);
        assertTrue(this.response.code() == arg0);
    }
}
