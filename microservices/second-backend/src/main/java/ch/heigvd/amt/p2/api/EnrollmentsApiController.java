package ch.heigvd.amt.p2.api;

import ch.heigvd.amt.p2.api.dto.EnrollmentDto;
import ch.heigvd.amt.p2.api.dto.PagedResponse;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.ForbiddenAccessException;
import ch.heigvd.amt.p2.helper.AuthHelper;
import ch.heigvd.amt.p2.model.Enrollment;
import ch.heigvd.amt.p2.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

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

    @Override
    public ResponseEntity<EnrollmentDto> createEnrollment(@ApiParam(value = "L'enrôlement à créer" ,required=true )  @Valid @RequestBody EnrollmentDto body) {
        try {

            Enrollment enrollment = this.enrollmentService.convertToEntity(body);
            enrollment.setOwner(AuthHelper.getUsername());
            enrollment = this.enrollmentService.create(enrollment);
            return new ResponseEntity<>(this.enrollmentService.convertToDto(enrollment), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteEnrollment(@ApiParam(value = "Identifiant de l'enrôlement",required=true) @PathVariable("id") Integer id) {
        try {
            if (AuthHelper.isAdmin() || this.enrollmentService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                this.enrollmentService.delete(id.longValue());
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
    public ResponseEntity<EnrollmentDto> getEnrollment(@ApiParam(value = "Identifiant de l'enrôlement",required=true) @PathVariable("id") Integer id) {
        try {
            if (AuthHelper.isAdmin() || this.enrollmentService.isOwner(id.longValue(), AuthHelper.getUsername())) {
                Enrollment enrollment = this.enrollmentService.get(id.longValue());
                return new ResponseEntity<>(this.enrollmentService.convertToDto(enrollment), HttpStatus.OK);
            }
        throw new ForbiddenAccessException();
    } catch (ResourceNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ForbiddenAccessException ex) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    }

    @Override
    public ResponseEntity<PagedResponse> getAllEnrollments(@ApiParam(value = "Numéro de page", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page, @ApiParam(value = "Taille de la page", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        Page enrollmentsPage = Page.empty();

        if (AuthHelper.isAdmin()) {
            enrollmentsPage = this.enrollmentService.getAll(PageRequest.of(page, size));
        } else {
            enrollmentsPage = this.enrollmentService.getAllByOwner(AuthHelper.getUsername(), PageRequest.of(page, size));
        }

        // Constitution de la paged response
        PagedResponse pagedResponse = new PagedResponse();
        pagedResponse.setContent(enrollmentsPage.getContent());
        pagedResponse.setPageNumber(enrollmentsPage.getNumber());
        pagedResponse.setPageSize(enrollmentsPage.getSize());
        pagedResponse.setTotalElements(enrollmentsPage.getNumberOfElements());
        pagedResponse.setTotalPages(enrollmentsPage.getTotalPages());

        return new ResponseEntity(pagedResponse, HttpStatus.OK);
    }

}
