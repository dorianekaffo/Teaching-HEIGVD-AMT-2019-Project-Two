package ch.heigvd.amt.p2.api;

import ch.heigvd.amt.p2.api.dto.PagedResponse;
import ch.heigvd.amt.p2.api.dto.StudentDto;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.ForbiddenAccessException;
import ch.heigvd.amt.p2.helper.AuthHelper;
import ch.heigvd.amt.p2.model.Student;
import ch.heigvd.amt.p2.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Override
    public ResponseEntity<StudentDto> createStudent(@ApiParam(value = "L'étudiant à créer" ,required=true )  @Valid @RequestBody StudentDto body) {

        Student student = this.studentService.convertToEntity(body);
        student.setOwner(AuthHelper.getUsername());
        student = this.studentService.create(student);
        return new ResponseEntity<>(this.studentService.convertToDto(student), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteStudent(@ApiParam(value = "identifiant de l'étudiant à supprimer",required=true) @PathVariable("id") Integer id) {
        try {

            if (AuthHelper.isAdmin() || this.studentService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                this.studentService.delete(id.longValue());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            throw new ForbiddenAccessException();
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<StudentDto> getStudent(@ApiParam(value = "Identifiant de l'étudiant",required=true) @PathVariable("id") Integer id) {
        try {

            if (AuthHelper.isAdmin() || this.studentService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                Student student = this.studentService.get(id.longValue());
                return new ResponseEntity(this.studentService.convertToDto(student), HttpStatus.OK);
            }
            throw new ForbiddenAccessException();
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<PagedResponse> getAllStudents(@ApiParam(value = "Numéro de page", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page, @ApiParam(value = "Taille de la page", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        Page studentsPage = Page.empty();

        if (AuthHelper.isAdmin()) {
            studentsPage = this.studentService.getAll(PageRequest.of(page, size));
        } else {
            studentsPage = this.studentService.getAllByOwner(AuthHelper.getUsername(), PageRequest.of(page, size));
        }
        // Constitution de la paged response
        PagedResponse pagedResponse = new PagedResponse();
        pagedResponse.setContent(studentsPage.getContent());
        pagedResponse.setPageNumber(studentsPage.getNumber());
        pagedResponse.setPageSize(studentsPage.getSize());
        pagedResponse.setTotalElements(studentsPage.getNumberOfElements());
        pagedResponse.setTotalPages(studentsPage.getTotalPages());

        return new ResponseEntity(pagedResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDto> updateStudent(@ApiParam(value = "Identifiant de l'étudiant à mettre à jour",required=true) @PathVariable("id") Integer id,@ApiParam(value = "Etudiant à mettre à jour" ,required=true )  @Valid @RequestBody StudentDto body) {
        try {

            if (AuthHelper.isAdmin() || this.studentService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                Student student = this.studentService.convertToEntity(body);
                student.setOwner(AuthHelper.getUsername());
                student = this.studentService.update(id.longValue(), student);
                return new ResponseEntity(this.studentService.convertToDto(student), HttpStatus.OK);
            }
            throw new ForbiddenAccessException();
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
