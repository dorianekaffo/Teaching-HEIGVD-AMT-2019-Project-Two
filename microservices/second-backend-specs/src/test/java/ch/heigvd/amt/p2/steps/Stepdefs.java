package ch.heigvd.amt.p2.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import ch.heigvd.amt.p2.api.CourseApi;
import ch.heigvd.amt.p2.api.StudentApi;
import ch.heigvd.amt.p2.api.EnrollmentApi;
import ch.heigvd.amt.p2.dto.Course;
import ch.heigvd.amt.p2.dto.Enrollment;
import ch.heigvd.amt.p2.dto.Student;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Stepdefs {

    // private final DefaultApi api = new DefaultApi();

    private CourseApi api = new CourseApi();
    private Course course;
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;


    @Then("^Je reçois une réponse de code (\\d+)$")
    public void jeReçoisUneRéponseDeCode(int arg0) {
        if (lastApiCallThrewException) {
            assertEquals(arg0, lastApiException.getCode());
        } else {
            assertEquals(arg0, lastApiResponse.getStatusCode());
        }
    }

    @When("^Je fais un POST vers le chemin \"([^\"]*)\"$")
    public void jeFaisUnPOSTVersLeChemin(String arg0) throws Throwable {
        try {
            lastApiResponse = api.createCourseWithHttpInfo(course);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Given("^Je veux créer un departement d'identifiant (\\d+) et d'intitulé \"([^\"]*)\"$")
    public void jeVeuxCréerUnDepartementDIdentifiantEtDIntitulé(int arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.course = new Course();
        this.course.setId((long) arg0);
        this.course.setName(arg1);
    }

    /*

        @Given("^there is an OpenAffect server")
    public void there_is_an_OpenAffect_server() throws Throwable {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.openaffect.server.url");
        api.getApiClient().setBasePath(url);
    }

    @Given("^I have an affective payload$")
    public void i_have_an_affective_payload() throws Throwable {
        Resource sensor = new Resource();
        sensor.setHref("https://cucumber.io");
        sensor.setType("testing tool");

        Resource subject = new Resource();
        subject.setHref("https://github.com/wasadigi");
        subject.setType("person");
        subject.getProperties().put("login", "wasadigi");
        subject.getProperties().put("name", "Olivier Liechti");

        Resource trigger = new Resource();
        trigger.setType("project");
        trigger.setHref("agent");
        trigger.getProperties().put("name", "Open Affect Server");

        Emotion emotion = new Emotion();
        emotion.setCategory("joy");
        emotion.setIntensity(1.0);

        measure = new Measure();
        measure.setSensor(sensor);
        measure.setSubject(subject);
        measure.setTimestamp(DateTime.now());
        measure.setTrigger(trigger);
        measure.setEmotion(emotion);
    }

    @When("^I POST it to the /measures endpoint$")
    public void i_POST_it_to_the_measures_endpoint() throws Throwable {
        try {
            lastApiResponse = api.reportMeasureWithHttpInfo(measure);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^I do a GET on the /measures endpoint$")
    public void i_do_a_GET_on_the_measures_endpoint() throws Throwable {

    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int expectedStatusCode) throws Throwable {
        if (lastApiCallThrewException) {
            assertEquals(expectedStatusCode, lastApiException.getCode());
        } else {
            assertEquals(expectedStatusCode, lastApiResponse.getStatusCode());
        }
    }

    @Then("^the payload is a non-empty list$")
    public void the_payload_is_a_non_empty_list() throws Throwable {
        List list = (List) lastApiResponse.getData();
        assertNotEquals(0, list.size());
    }

    */
}
