package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.dto.CourseDto;
import ch.heigvd.amt.p2.dto.EnrollmentDto;
import ch.heigvd.amt.p2.dto.PagedResponse;
import ch.heigvd.amt.p2.dto.StudentDto;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Properties;

import java.io.IOException;

import static org.junit.Assert.*;

public class Stepdefs {

    // private final DefaultApi api = new DefaultApi();

    private CourseApi courseApi = new CourseApi();
    private StudentApi studentApi = new StudentApi();
    private EnrollmentApi enrollmentApi = new EnrollmentApi();

    private Integer id;

    private CourseDto course;
    private StudentDto student;
    private EnrollmentDto enrollment;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;

    private String token;

    @Given("^there is a second server")
    public void there_is_a_second_server() throws Throwable {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));

        String secondServerUrl = properties.getProperty("io.openaffect.server.url");
        courseApi.getApiClient().setBasePath(secondServerUrl);
    }

    @Then("^Je reçois une réponse de code (\\d+)$")
    public void jeReçoisUneRéponseDeCode(int arg0) {
        if (lastApiCallThrewException) {
            assertEquals(arg0, lastApiException.getCode());
        } else {
            assertEquals(arg0, lastApiResponse.getStatusCode());
        }
    }

    @Given("^J'ai un cours à créer$")
    public void jAiUnCoursÀCréer() {
        Course course = new Course();
        course.setName("Programmation");
    }

    @When("^Je fais un POST pour créer un étudiant$")
    public void jeFaisUnPOSTPourCréerUnÉtudiant() {
        try {
            lastApiResponse = courseApi.createCourseWithHttpInfo(course);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Then("^Je vois mon cours$")
    public void jeVoisMonCours() {
        (PagedResponse)lastApiResponse.getData()
    }

    @And("^Le résultat est sous forme paginée$")
    public void leRésultatEstSousFormePaginéé() {
        assertTrue(lastApiResponse.getData() instanceof PagedResponse);
    }

    @When("^Je fais un GET vers le chemin /courses avec le numéro de page (\\d+) et la taille de page (\\d+)$")
    public void jeFaisUnGETVersLeCheminCoursesAvecLeNuméroDePageEtLaTailleDePage(int pageNumber, int pageSize) {
        try {
            lastApiResponse = courseApi.getAllCoursesWithHttpInfo(pageNumber, pageSize);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un GET vers le chemin /students\\?page=(\\d+)&size=(\\d+)$")
    public void jeFaisUnGETVersLeCheminStudentsPageSize(int pageNumber, int pageSize) {
        try {
            lastApiResponse = studentApi.getAllStudentsWithHttpInfo(pageNumber, pageSize);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Given("^J'ai un cours à ajouter$")
    public void jAiUnCoursÀAjouter() {
        course = new CourseDto();
        course.setTitle("BDD avec Cucumber");
    }

    @When("^Je fais un POST vers le chemin /courses pour créer un cours$")
    public void jeFaisUnPOSTVersLeCheminCoursesPourCréerUnCours() {
        try {
            lastApiResponse = courseApi.createCourseWithHttpInfo(course);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je fais un GET pour récupérer mon nouveau cours$")
    public void jeFaisUnGETPourRécupérerMonNouveauCours() {
        try {
            assertTrue(lastApiResponse.getData() instanceof CourseDto);
            lastApiResponse = courseApi.getCourseWithHttpInfo(((CourseDto)lastApiResponse.getData()).getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Les cours correspondent$")
    public void lesCoursCorrespondent() {
        assertTrue(lastApiResponse.getData() instanceof CourseDto);
        assertEquals(((CourseDto)lastApiResponse.getData()).getTitle(), this.course.getTitle());
    }

    @Given("^J'ai l'identifiant (\\d+) d'une ressource$")
    public void jAiLIdentifiantDUneRessource(int identifiant) {
        this.id = identifiant;
    }


    @And("^J'ai un cours à mettre à jour$")
    public void jAiUnCoursÀMettreÀJour() {
        course = new CourseDto();
        course.setId(this.id);
        course.setTitle("Premier pas avec Spring Boot");
    }

    @When("^Je fais un PUT vers le chemin /courses/(\\d+) avec des données$")
    public void jeFaisUnPUTVersLeCheminCoursesAvecDesDonnées(int arg0) {
        try {
            lastApiResponse = courseApi.updateCourseWithHttpInfo(this.id, this.course);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un GET vers le chemin /courses/(\\d+)$")
    public void jeFaisUnGETVersLeCheminCourses(int identifiant) {
        try {
            lastApiResponse = courseApi.getCourseWithHttpInfo(identifiant);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un DELETE vers le chemin /courses/(\\d+) pour le supprimer$")
    public void jeFaisUnDELETEVersLeCheminCoursesPourLeSupprimer(int identifiant) {
        try {
            lastApiResponse = courseApi.deleteCourseWithHttpInfo(identifiant);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un PUT vers le chemin /students/(\\d+)$")
    public void jeFaisUnPUTVersLeCheminStudents(int identifiant) {
        try {
            lastApiResponse = studentApi.updateStudentWithHttpInfo(identifiant, student);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je veux mettre à jour l'étudiant correspondant$")
    public void jeVeuxMettreÀJourLÉtudiantCorrespondant() {
        student = new StudentDto();
        student.setId(this.id);
        student.setFirstname("Olivier");
        student.setLastname("Liechti");
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int expectedStatusCode) throws Throwable {
        if (lastApiCallThrewException) {
            assertEquals(expectedStatusCode, lastApiException.getCode());
        } else {
            assertEquals(expectedStatusCode, lastApiResponse.getStatusCode());
        }
    }

    @When("^Je fais un GET vers le chemin /students/(\\d+)$")
    public void jeFaisUnGETVersLeCheminStudents(int identifiant) {
        try {
            lastApiResponse = studentApi.getStudentWithHttpInfo(identifiant);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un DELETE vers le chemin /students/(\\d+)$")
    public void jeFaisUnDELETEVersLeCheminStudents(int identifiant) {
        try {
            lastApiResponse = studentApi.deleteStudentWithHttpInfo(identifiant);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un POST vers le chemin /students$")
    public void jeFaisUnPOSTVersLeCheminStudents() {
        try {
            lastApiResponse = studentApi.createStudentWithHttpInfo(student);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Given("^Je veux créer un étudiant de prenom \"([^\"]*)\" et de nom \"([^\"]*)\"$")
    public void jeVeuxCréerUnÉtudiantDePrenomEtDeNom(String firstname, String lastname) throws Throwable {
       student = new StudentDto();
       student.setFirstname(firstname);
       student.setLastname(lastname);
    }

    @Given("^Je m'authentifie avec l'email \"([^\"]*)\" et le mot de passe \"([^\"]*)\"$")
    public void jeMAuthentifieAvecLEmailEtLeMotDePasse(String arg0, String arg1) throws Throwable {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));

        System.out.println("Properties: " + properties);
        OkHttpClient client = new OkHttpClient();
        String authServerUrl = properties.getProperty("ch.heigvd.amt.p2.first-server.url");

        System.out.println("Auth Server URL: " + authServerUrl);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, "{\"email\": " + arg0 + ", \"password\" : " + arg1 + "}");
        Request request = new Request.Builder().url(authServerUrl + "/auth/login").post(requestBody).build();
        Response response = client.newCall(request).execute();

        System.out.println("La réponse: " + response);
        assertTrue(response.isSuccessful());
        this.token = response.body().toString();
        System.out.println("Le token: " + this.token);
        assertFalse(this.token.isEmpty());
    }

    @When("^Je fais un POST vers le chemin /enrollments$")
    public void jeFaisUnPOSTVersLeCheminEnrollments() {
        try {
            lastApiResponse = enrollmentApi.createEnrollmentWithHttpInfo(enrollment);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }


    @When("^Je fais un DELETE vers le chemin /enrollments/(\\d+)$")
    public void jeFaisUnDELETEVersLeCheminEnrollments(int arg0) {
        try {
            lastApiResponse = enrollmentApi.deleteEnrollmentWithHttpInfo(arg0);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un POST vers le chemin /enrollments\\?page=(\\d+)&size=(\\d+)$")
    public void jeFaisUnPOSTVersLeCheminEnrollmentsPageSize(int arg0, int arg1) {
        try {
            lastApiResponse = enrollmentApi.getAllEnrollmentsWithHttpInfo(arg0, arg1);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }
}
