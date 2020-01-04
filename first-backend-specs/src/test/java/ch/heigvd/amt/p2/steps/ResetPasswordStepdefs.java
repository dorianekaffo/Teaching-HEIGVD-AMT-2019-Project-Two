package ch.heigvd.amt.p2.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ResetPasswordStepdefs {

    @Given("I have an account with email {string}")
    public void iHaveAnAccountWithEmail(String arg0) {
    }

    @When("I DELETE to the \\/users\\/password endpoint")
    public void iDELETEToTheUsersPasswordEndpoint() {
    }

    @Then("I get a {int} response message and an email sent to my account")
    public void iGetAResponseMessageAndAnEmailSentToMyAccount(int arg0) {
    }

    @When("I GET to \\/users\\/password with token")
    public void iGETToUsersPasswordWithToken() {
    }

    @Then("I a {int} response")
    public void iAResponse(int arg0) {
    }

    @When("I POST to the \\/users\\/password with new password {string}")
    public void iPOSTToTheUsersPasswordWithNewPassword(String arg0) {
    }

    @Then("I get a {int} response confirming my new password")
    public void iGetAResponseConfirmingMyNewPassword(int arg0) {
    }
}
