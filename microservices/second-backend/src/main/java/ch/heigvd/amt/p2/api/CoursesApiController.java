package ch.heigvd.amt.p2.api;

import ch.heigvd.amt.p2.api.dto.CourseDto;
import ch.heigvd.amt.p2.api.dto.PagedResponse;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.ForbiddenAccessException;
import ch.heigvd.amt.p2.helper.AuthHelper;
import ch.heigvd.amt.p2.model.Course;
import ch.heigvd.amt.p2.service.CourseService;
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

    @Override
    public ResponseEntity<CourseDto> createCourse(@ApiParam(value = "Cours à créer" ,required=true )  @Valid @RequestBody CourseDto courseDto) {

        Course course = this.courseService.convertToEntity(courseDto);
        course.setOwner(AuthHelper.getUsername());
        course = this.courseService.create(course);
        return new ResponseEntity<>(this.courseService.convertToDto(course), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCourse(@ApiParam(value = "identifiant du cours à supprimer",required=true) @PathVariable("id") Integer id) {
        try {

            if (!AuthHelper.isAdmin() && !this.courseService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                throw new ForbiddenAccessException();
            }

            this.courseService.delete(id.longValue());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<CourseDto> getCourse(@ApiParam(value = "L'identifiant du cours",required=true) @PathVariable("id") Integer id) {

        try {

            if (!AuthHelper.isAdmin() && !this.courseService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                throw new ForbiddenAccessException();
            }
            Course course = this.courseService.get(id.longValue());
            return new ResponseEntity(this.courseService.convertToDto(course), HttpStatus.OK);

        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ForbiddenAccessException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<PagedResponse> getAllCourses(@ApiParam(value = "Numéro de page", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page, @ApiParam(value = "Taille de la page", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        Page coursesPage = Page.empty();

        if (AuthHelper.isAdmin()) {
            coursesPage = this.courseService.getAll(PageRequest.of(page, size));
        } else {
            coursesPage = this.courseService.getAllByOwner(AuthHelper.getUsername(), PageRequest.of(page, size));
        }

        // Constitution de la paged response
        PagedResponse pagedResponse = new PagedResponse();
        pagedResponse.setContent(coursesPage.getContent());
        pagedResponse.setPageNumber(coursesPage.getNumber());
        pagedResponse.setPageSize(coursesPage.getSize());
        pagedResponse.setTotalElements(coursesPage.getNumberOfElements());
        pagedResponse.setTotalPages(coursesPage.getTotalPages());

        return new ResponseEntity(pagedResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CourseDto> updateCourse(@ApiParam(value = "identifiant du cours à mettre à jour",required=true) @PathVariable("id") Integer id,@ApiParam(value = "Le cours à mettre à jour" ,required=true )  @Valid @RequestBody CourseDto body) {
        try {
            if (!AuthHelper.isAdmin() && !this.courseService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                throw new ForbiddenAccessException();
            }

            Course course = this.courseService.convertToEntity(body);
            course.setOwner(AuthHelper.getUsername());
            course = this.courseService.update(id.longValue(), course);
            return new ResponseEntity(this.courseService.convertToDto(course), HttpStatus.OK);

    } catch (ResourceNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ForbiddenAccessException ex) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    }

}
