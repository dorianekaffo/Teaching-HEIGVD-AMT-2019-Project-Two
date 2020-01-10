package ch.heigvd.amt.p2.endpoints.api;

import java.math.BigDecimal;

import ch.heigvd.amt.p2.model.Course;
import ch.heigvd.amt.p2.service.CourseService;
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
public class CoursesApiController implements CoursesApi {

    private static final Logger log = LoggerFactory.getLogger(CoursesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CourseService courseService;

    @org.springframework.beans.factory.annotation.Autowired
    public CoursesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Course> createCourse(@ApiParam(value = "Cours à créer" ,required=true )  @Valid @RequestBody Course course) {
        return new ResponseEntity<>(this.courseService.create(course), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCourse(@ApiParam(value = "identifiant du cours à supprimer",required=true) @PathVariable("id") Long id) {
        this.courseService.delete(id);
        return new ResponseEntity<>("Entité Supprimé", HttpStatus.OK);
    }

    public ResponseEntity<Course> getCourseById(@ApiParam(value = "L'identifiant du cours",required=true) @PathVariable("id") Long id) {
        return new ResponseEntity(this.courseService.get(id), HttpStatus.OK);
    }

    public ResponseEntity<List<Course>> getCourses(Pageable pgble) {
        return new ResponseEntity(this.courseService.getAll(pgble), HttpStatus.OK);
    }

    public ResponseEntity<Course> updateCourse(@ApiParam(value = "identifiant du cours à mettre à jour",required=true) @PathVariable("id") Long id,@ApiParam(value = "Le cours à mettre à jour" ,required=true )  @Valid @RequestBody Course course) {
        return new ResponseEntity(this.courseService.update(id, course), HttpStatus.OK);
    }

}
