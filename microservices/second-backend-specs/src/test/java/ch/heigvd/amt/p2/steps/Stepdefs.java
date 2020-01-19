package ch.heigvd.amt.p2.steps;

import ch.heigvd.amt.p2.api.*;
import ch.heigvd.amt.p2.dto.*;
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

    private CourseApi courseApi = new CourseApi();
    private StudentApi studentApi = new StudentApi();
    private EnrollmentApi enrollmentApi = new EnrollmentApi();

    // Les api pour le premier backend
    private AuthApi authApi = new AuthApi();
    private UserApi userApi = new UserApi();

    private Integer id;

    private CourseDto course;
    private StudentDto student;
    private EnrollmentDto enrollment;

    private UserDto user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;
    private Object body;

    private CourseDto createdCourse;
    private StudentDto createdStudent;

    private String token;

    @Given("^Il y a un serveur$")
    public void there_is_a_second_server() throws Throwable {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));

        // Initialisation du second serveur
        String secondServerUrl = properties.getProperty("ch.heigvd.amt.p2.second-server.url");

        courseApi.getApiClient().setHttpClient(courseApi.getApiClient().getHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS).build());
        studentApi.getApiClient().setHttpClient(studentApi.getApiClient().getHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS).build());
        enrollmentApi.getApiClient().setHttpClient(enrollmentApi.getApiClient().getHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS).build());

        courseApi.getApiClient().setBasePath(secondServerUrl);
        studentApi.getApiClient().setBasePath(secondServerUrl);
        enrollmentApi.getApiClient().setBasePath(secondServerUrl);

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

    @When("^Je fais un POST vers le chemin /courses pour créer un cours$")
    public void jeFaisUnPOSTVersLeCheminCoursesPourCréerUnCours() {
        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.createCourseWithHttpInfo(course);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
            System.out.println("Exception: " + e);
        }
    }

    @And("^Je fais un GET pour récupérer mon nouveau cours$")
    public void jeFaisUnGETPourRécupérerMonNouveauCours() {
        try {
            assertTrue(lastApiResponse.getData() instanceof CourseDto);
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.getCourseWithHttpInfo(((CourseDto) lastApiResponse.getData()).getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Les cours correspondent$")
    public void lesCoursCorrespondent() {
        assertTrue(lastApiResponse.getData() instanceof CourseDto);
        assertEquals(((CourseDto) lastApiResponse.getData()).getTitle(), this.course.getTitle());
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
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
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

    @Given("^Je m'authentifie avec l'email \"([^\"]*)\" et le mot de passe \"([^\"]*)\"$")
    public void jeMAuthentifieAvecLEmailEtLeMotDePasse(String email, String password) throws Throwable {

        Credentials credentials = new Credentials();
        credentials.setEmail(email);
        credentials.setPassword(password);

        try {
            lastApiResponse = authApi.loginWithHttpInfo(credentials);
            lastApiCallThrewException = false;
            assertEquals(200, lastApiResponse.getStatusCode());
            this.token = (String) lastApiResponse.getData();
            System.out.println("Le token: " + this.token);
            assertFalse(this.token.isEmpty());
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }

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


    @When("^Je fais un DELETE vers le chemin /enrollments/(\\d+)$")
    public void jeFaisUnDELETEVersLeCheminEnrollments(int arg0) {
        try {
            enrollmentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = enrollmentApi.deleteEnrollmentWithHttpInfo(arg0);
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

    @And("^Il y a un serveur du premier backend$")
    public void ilYAUnServeurDuPremierBackend() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        // Initialisation du second serveur
        String firstServerUrl = properties.getProperty("ch.heigvd.amt.p2.first-server.url");

        authApi.getApiClient().setHttpClient(authApi.getApiClient().getHttpClient().newBuilder().readTimeout(45, TimeUnit.SECONDS).build());
        userApi.getApiClient().setHttpClient(userApi.getApiClient().getHttpClient().newBuilder().readTimeout(45, TimeUnit.SECONDS).build());

        authApi.getApiClient().setBasePath(firstServerUrl);
        userApi.getApiClient().setBasePath(firstServerUrl);
    }

    @And("^J'ai un utilisateur de prénom \"([^\"]*)\", de nom \"([^\"]*)\", d'email \"([^\"]*)\", de mot de passe \"([^\"]*)\" et de role \"([^\"]*)\"$")
    public void jAiUnUtilisateurDePrénomDeNomDEmailDeMotDePasseEtDeRole(String firstName, String lastName, String email, String password, String role) throws Throwable {
        this.user = new UserDto();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
    }

    @And("^Je fais un POST vers le chemin /users$")
    public void jeFaisUnPOSTVersLeCheminUsers() {
        try {
            userApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = userApi.createWithHttpInfo(this.user);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @And("^Je recupère le nouveau cours$")
    public void jeRecupèreLeNouveauCours() {
        this.course = (CourseDto) lastApiResponse.getData();
    }

    @And("^Je récupère le nouvel étudiant$")
    public void jeRécupèreLeNouvelÉtudiant() {
        this.student = (StudentDto) lastApiResponse.getData();
    }

    @And("^Je fais un GET vers le chemin /students avec le nouvel étudiant$")
    public void jeFaisUnGETVersLeCheminStudentsAvecLeNouvelÉtudiant() {
        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            System.out.println("Student: " + this.student + ", id: " + this.student.getId());
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
            System.out.println("Course: " + this.course + ", id: " + this.course.getId());
            lastApiResponse = courseApi.getCourseWithHttpInfo(this.course.getId());
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }


    @And("^Je fais un PUT vers le chemin /students avec le nouvel étudiant avec son nouveau nom \"([^\"]*)\"$")
    public void jeFaisUnPUTVersLeCheminStudentsAvecLeNouvelÉtudiantAvecSonNouveauNom(String lastname) throws Throwable {
        this.student.setLastname(lastname);
        try {
            studentApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = studentApi.updateStudentWithHttpInfo(this.student.getId(), this.student);
            lastApiCallThrewException = false;
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiException = e;
        }
    }

    @When("^Je fais un PUT vers le chemin /courses avec le nouveau cours avec son nouveau titre \"([^\"]*)\"$")
    public void jeFaisUnPUTVersLeCheminCoursesAvecLeNouveauCoursAvecSonNouveauTitre(String arg0) throws Throwable {

        this.course.setTitle(arg0);

        try {
            courseApi.getApiClient().addDefaultHeader("Authorization", "Bearer " + this.token);
            lastApiResponse = courseApi.updateCourseWithHttpInfo(this.course.getId(), course);
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
}