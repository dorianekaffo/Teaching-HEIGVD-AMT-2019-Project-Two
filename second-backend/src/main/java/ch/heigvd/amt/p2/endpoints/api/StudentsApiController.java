package ch.heigvd.amt.p2.endpoints.api;

import java.math.BigDecimal;

import ch.heigvd.amt.p2.model.Student;
import ch.heigvd.amt.p2.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
public class StudentsApiController implements StudentsApi {

    private static final Logger log = LoggerFactory.getLogger(StudentsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private StudentService studentService;

    @org.springframework.beans.factory.annotation.Autowired
    public StudentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Student> createStudent(@ApiParam(value = "L'étudiant à créer" ,required=true )  @Valid @RequestBody Student student) {
        return new ResponseEntity<>(this.studentService.create(student), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteStudent(@ApiParam(value = "identifiant de l'étudiant à supprimer",required=true) @PathVariable("id") Long id) {
        this.studentService.delete(id);
        return new ResponseEntity<>("Entité Supprimé", HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentById(@ApiParam(value = "Identifiant de l'étudiant",required=true) @PathVariable("id") Long id) {
        return new ResponseEntity(this.studentService.get(id), HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getStudents(Pageable pgble) {
        return new ResponseEntity(this.studentService.getAll(pgble), HttpStatus.OK);
    }

    public ResponseEntity<Void> updateStudent(@ApiParam(value = "Identifiant de l'étudiant à mettre à jour",required=true) @PathVariable("id") Long id,@ApiParam(value = "Etudiant à mettre à jour" ,required=true )  @Valid @RequestBody Student student) {
        return new ResponseEntity(this.studentService.update(id, student), HttpStatus.OK);
    }

}
