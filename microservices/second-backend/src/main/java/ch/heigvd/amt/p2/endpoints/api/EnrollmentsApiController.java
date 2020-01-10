package ch.heigvd.amt.p2.endpoints.api;

import java.math.BigDecimal;
import ch.heigvd.amt.p2.model.Enrollment;
import ch.heigvd.amt.p2.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-02T04:27:12.447Z")

@Controller
public class EnrollmentsApiController implements EnrollmentsApi {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private EnrollmentService enrollmentService;

    @org.springframework.beans.factory.annotation.Autowired
    public EnrollmentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Enrollment> createEnrollment(@ApiParam(value = "L'enrôlement à créer" ,required=true )  @Valid @RequestBody Enrollment enrollment) {
        return new ResponseEntity<>(this.enrollmentService.create(enrollment), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEnrollment(@ApiParam(value = "Identifiant de l'enrôlement",required=true) @PathVariable("id") Long id) {
        this.enrollmentService.delete(id);
        return new ResponseEntity<>("Entité supprimé", HttpStatus.OK);
    }

    public ResponseEntity<Enrollment> getEnrollmentById(@ApiParam(value = "Identifiant de l'enrôlement",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Enrollment>(objectMapper.readValue("{  \"person\" : {    \"name\" : \"name\",    \"id\" : 0  },  \"group\" : {    \"name\" : \"name\",    \"id\" : 0  }}", Enrollment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Enrollment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Enrollment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Enrollment>> getEnrollments() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Enrollment>>(objectMapper.readValue("[ {  \"name\" : \"name\",  \"id\" : 0}, {  \"name\" : \"name\",  \"id\" : 0} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Enrollment>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Enrollment>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
