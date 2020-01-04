package ch.heigvd.amt.p2.steps;


import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.*;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuthStepdefs {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    String email;
    String password;
    OkHttpClient client = new OkHttpClient();
    Response response;
    String token;

    @Given("^There a User server and auth server$")
    public void thereAUserServerAndAuthServer() {
    }

    @Given("^I have a the email \"([^\"]*)\" and a password \"([^\"]*)\"$")
    public void iHaveATheEmailAndAPassword(String email, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.email = email;
        this.password = password;
    }

    @When("^I \"([^\"]*)\" it to the /login endpoint$")
    public void iItToTheLoginEndpoint(String arg0) throws Throwable {
        RequestBody body = RequestBody.create(JSON, "{\"email\": \"" + this.email +"\", \"password\": \""+ this.password + "\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/auth/login")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.token = response.body().string();
        }
    }

    @Then("^I receive a (\\d+) status code and a token$")
    public void iReceiveAStatusCodeAndAToken(int arg0) throws IOException {
        assertTrue(this.response.code() == arg0);
        assertFalse(token.isEmpty());
        System.out.println("Body of the request: " + token);
    }

    @Given("^I am logged to the system$")
    public void iAmLoggedToTheSystem() throws IOException {
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

    @When("^I \"([^\"]*)\" it to the /logout endpoint$")
    public void iItToTheLogoutEndpoint(String arg0) throws Throwable {
        RequestBody body = RequestBody.create(JSON, "{\"email\": \"" + this.email +"\", \"password\": \""+ this.password + "\"}");
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.token)
                .url("http://localhost:8080/auth/logout")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int arg0) {
        System.out.println("Voici la reponse: " + this.response);
        assertTrue(this.response.code() == arg0);
    }
}
