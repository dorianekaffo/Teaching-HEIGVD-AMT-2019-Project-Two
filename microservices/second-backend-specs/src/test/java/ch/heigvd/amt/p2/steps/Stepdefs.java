package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.api.*;
import ch.heigvd.amt.p2.dto.*;
import ch.heigvd.amt.p2.helpers.Environment;
import ch.heigvd.amt.p2.helpers.Role;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ch.heigvd.amt.p2.ApiException;
import ch.heigvd.amt.p2.ApiResponse;
import java.util.Properties;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Stepdefs {

    // private final DefaultApi api = new DefaultApi();
    private Environment environment = new Environment();

    private CourseApi courseApi;
    private StudentApi studentApi;
    private EnrollmentApi enrollmentApi;

    private Integer id;

    private CourseDto course;
    private StudentDto student;
    private EnrollmentDto enrollment;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private Object body;

    private CourseDto createdCourse;
    private StudentDto createdStudent;

    private String token;

    public Stepdefs() throws IOException {
        this.environment = new Environment();
        this.studentApi = this.environment.getStudentApi();
        this.courseApi = this.environment.getCourseApi();
        this.enrollmentApi = this.environment.getEnrollmentApi();
    }

    @Given("^Il y a un serveur$")
    public void there_is_a_second_server() throws Throwable {
        assertNotNull(this.studentApi);
        assertNotNull(this.courseApi);
        assertNotNull(this.enrollmentApi);
    }

    @Then("^Je reçois une réponse de code (\\d+)$")
    public void jeReçoisUneRéponseDeCode(int arg0) {
        if (lastApiCallThrewException) {
            assertEquals(arg0, lastApiException.getCode());
        } else {
            assertEquals(arg0, lastApiResponse.getStatusCode());
        }
    }

    @Given("^J'ai un cours intitulé \"([^\"]*)\"$")
    public void jAiUnCoursDeTitre(String title) throws Throwable {
        this.course = new CourseDto();
        course.setTitle(title);
    }

    @When("^Je fais un POST pour créer un étudiant$")
    public void jeFaisUnPOSTPourCréerUnÉtudiant() {
        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = studentApi.createStudentWithHttpInfo(student);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Then("^Je vois mon cours$")
    public void jeVoisMonCours() {
        assertTrue(lastApiResponse.getData() instanceof PagedResponse);
    }

    @And("^Le résultat est sous forme paginée$")
    public void leRésultatEstSousFormePaginéé() {
        assertTrue(lastApiResponse.getData() instanceof PagedResponse);
    }

    @When("^Je fais un GET vers le chemin /courses avec le numéro de page (\\d+) et la taille de page (\\d+)$")
    public void jeFaisUnGETVersLeCheminCoursesAvecLeNuméroDePageEtLaTailleDePage(int pageNumber, int pageSize) {
        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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

    @When("^Je fais un POST vers le chemin /courses$")
    public void jeFaisUnPOSTVersLeCheminCoursesPourCréerUnCours() {
        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.createCourseWithHttpInfo(course);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Le cours est modifié$")
    public void leCoursEstModifie() {
        assertTrue(lastApiResponse.getData() instanceof CourseDto);
        assertNotEquals(((CourseDto) lastApiResponse.getData()).getTitle(), this.course.getTitle());
    }

    @And("^L'étudiant est modifié$")
    public void lEtudiantEstModifie() {
        assertTrue(lastApiResponse.getData() instanceof StudentDto);
        assertNotEquals(((StudentDto) lastApiResponse.getData()).getLastname(), this.student.getLastname());
    }

    @And("^J'ai un cours à mettre à jour$")
    public void jAiUnCoursÀMettreÀJour() {
        course = new CourseDto();
        course.setId(this.id);
        course.setTitle("Premier pas avec Spring Boot");
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

    @When("^Je fais un POST vers le chemin /students$")
    public void jeFaisUnPOSTVersLeCheminStudents() {
        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = studentApi.createStudentWithHttpInfo(student);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @Given("^J'ai un étudiant de prenom \"([^\"]*)\" et de nom \"([^\"]*)\"$")
    public void jAiUnEtudiantDePrenomEtDeNom(String firstname, String lastname) throws Throwable {
        student = new StudentDto();
        student.setFirstname(firstname);
        student.setLastname(lastname);
    }

    @When("^Je fais un POST vers le chemin /enrollments$")
    public void jeFaisUnPOSTVersLeCheminEnrollments() {
        try {
            enrollmentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = enrollmentApi.createEnrollmentWithHttpInfo(enrollment);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un GET vers le chemin /enrollments\\?page=(\\d+)&size=(\\d+)$")
    public void jeFaisUnGETVersLeCheminEnrollmentsPageSize(int arg0, int arg1) {
        try {
            enrollmentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = enrollmentApi.getAllEnrollmentsWithHttpInfo(arg0, arg1);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Le nombre d'enrollments n'est pas zéro$")
    public void leNombreDEnrollmentsNEstPasZéro() {
        this.body = this.lastApiResponse.getData();
        assertTrue(body instanceof PagedResponse);
        assertNotEquals(0, ((PagedResponse) body).getContent().size());
    }

    @Given("^J'ai un enrôlement d'un étudiant d'identifiant (\\d+) à un cours d'identifiant (\\d+)$")
    public void jAiUnEnrôlementDUnÉtudiantDIdentifiantÀUnCoursDIdentifiant(int studentId, int courseId) {
        enrollment = new EnrollmentDto();
        enrollment.setStudent(studentId);
        enrollment.setCourse(courseId);
    }

    @And("^Je récupère le nouveau cours$")
    public void jeRecupereLeNouveauCours() {
        this.course = (CourseDto) lastApiResponse.getData();
    }

    @And("^Je récupère le nouvel étudiant$")
    public void jeRecupereLeNouvelÉtudiant() {
        this.student = (StudentDto) lastApiResponse.getData();
    }

    @And("^Je fais un GET vers le chemin /students avec le nouvel étudiant$")
    public void jeFaisUnGETVersLeCheminStudentsAvecLeNouvelEtudiant() {
        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = studentApi.getStudentWithHttpInfo(this.student.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un GET vers le chemin /courses avec le nouveau cours$")
    public void jeFaisUnGETVersLeCheminCoursesAvecLeNouveauCours() {
        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.getCourseWithHttpInfo(this.course.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }


    @And("^Je fais un PUT vers le chemin /students avec le nouvel étudiant avec son nouveau nom \"([^\"]*)\"$")
    public void jeFaisUnPUTVersLeCheminStudentsAvecLeNouvelÉtudiantAvecSonNouveauNom(String lastname) throws Throwable {

        StudentDto updatedStudent = new StudentDto();
        updatedStudent.setLastname(lastname);
        updatedStudent.setId(this.student.getId());
        updatedStudent.setFirstname(this.student.getFirstname());
        updatedStudent.setOwner(this.student.getOwner());

        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = studentApi.updateStudentWithHttpInfo(this.student.getId(), updatedStudent);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un PUT vers le chemin /courses avec le nouveau cours avec son nouveau titre \"([^\"]*)\"$")
    public void jeFaisUnPUTVersLeCheminCoursesAvecLeNouveauCoursAvecSonNouveauTitre(String arg0) throws Throwable {

        CourseDto updatedCourse = new CourseDto();
        updatedCourse.setId(this.course.getId());
        updatedCourse.setTitle(arg0);

        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.updateCourseWithHttpInfo(this.course.getId(), updatedCourse);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je fais un DELETE vers le chemin /students avec le nouvel étudiant$")
    public void jeFaisUnDELETEVersLeCheminStudentsAvecLeNouvelÉtudiant() {
        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = studentApi.deleteStudentWithHttpInfo(this.student.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un DELETE vers le chemin /courses avec le nouveau cours$")
    public void jeFaisUnDELETEVersLeCheminCoursesAvecLeNouveauCours() {
        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.deleteCourseWithHttpInfo(this.course.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je m'assure que le owner est \"([^\"]*)\"$")
    public void jeMAssureQueLeOwnerEst(String ownerEmail) throws Throwable {
        body = lastApiResponse.getData();
        assertTrue(body instanceof CourseDto || body instanceof StudentDto);
        if (body instanceof CourseDto) {
            assertEquals(ownerEmail, ((CourseDto) body).getOwner());
        } else if (body instanceof StudentDto) {
            assertEquals(ownerEmail, ((StudentDto) body).getOwner());
        }
    }

    @And("^J'ai un enrôlement fait avec l'étudiant et le cours donné$")
    public void jAiUnEnrôlementFaitAvecLÉtudiantEtLeCoursDonné() {
        enrollment = new EnrollmentDto();
        enrollment.setStudent(this.student.getId());
        enrollment.setCourse(this.course.getId());
    }

    @And("^Je m'authentifie en tant qu'utilisateur avec l'email \"([^\"]*)\" et le rôle \"([^\"]*)\"$")
    public void jeMAuthentifieEnTantQuUtilisateurAvecLEmailEtLeRôle(String email, String role) throws Throwable {
        this.token = this.environment.getFakeToken(email, Role.valueOf(role));
    }

    @And("^Je récupère le nouvel enrollment$")
    public void jeRécupèreLeNouvelEnrollment() {
        this.enrollment = (EnrollmentDto)lastApiResponse.getData();
    }


    @And("^Je fais un GET vers le chemin /enrollments avec le nouvel enrôlement$")
    public void jeFaisUnGETVersLeCheminEnrollmentsAvecLeNouveauEnrollment() {
        try {
            enrollmentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = enrollmentApi.getEnrollmentWithHttpInfo(this.enrollment.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un DELETE vers le chemin /enrollments avec le nouvel enrôlement$")
    public void jeFaisUnDELETEVersLeCheminEnrollmentsAvecLeNouvelEnrôlement() {
        try {
            enrollmentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = enrollmentApi.deleteEnrollmentWithHttpInfo(this.enrollment.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }
}