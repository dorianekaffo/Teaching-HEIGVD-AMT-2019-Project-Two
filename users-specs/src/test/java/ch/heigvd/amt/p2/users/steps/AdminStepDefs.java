package ch.heigvd.amt.p2.users.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import okhttp3.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminStepDefs {


    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    Response response;
    String token;
    String body;
    String email;

    @Given("There a User server and I am logged as admin")
    public void thereAUserServerAndIAmLoggedAsAdmin() throws IOException {
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

    @Given("I am logged as an admin")
    public void iAmLoggedAsAnAdmin() {
    }

    @And("I have the email {string} of a user")
    public void iHaveTheEmailOfAUser(String arg0) {
        this.email = arg0;
    }

    @When("I {string} to the \\/users\\/block\\/{string} endpoint")
    public void iToTheUsersBlockEndpoint(String arg0, String arg1) throws IOException {

        if (arg0.equals("PUT")) {

            RequestBody body = RequestBody.create(JSON, "{\"email\": \"dorianekaffo@gmail.com\", \"password\": \"administrator\"}");
            Request request = new Request.Builder()
                    .url("http://localhost:8080/auth/login")
                    .put(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                this.response = response;
                this.token = response.body().string();
            }
        } else if (arg0.equals("DELETE")){
            RequestBody body = RequestBody.create(JSON, "{\"email\": \"dorianekaffo@gmail.com\", \"password\": \"administrator\"}");
            Request request = new Request.Builder()
                    .url("http://localhost:8080/users/block" + arg1)
                    .delete(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                this.response = response;
                this.token = response.body().string();
            }
        }
    }

    @Given("I have the email of a user")
    public void iHaveTheEmailOfAUser() {
    }

    @And("There is another user to block with id {string}")
    public void thereIsAnotherUserToBlockWithId(String arg0) throws IOException {
        String userPayload = "{\"email\": \"" + arg0 + "\", \"firstname\": \"Celine\", \"lastname\": \"Dion\", \"password\": \"CelineDion\"}";
        RequestBody body = RequestBody.create(JSON, userPayload);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.token)
                .url("http://localhost:8080/users/" + arg0)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            this.response = response;
            this.body = response.body().string();
            assertEquals(this.response.code(), arg0);
        }

    }
}
