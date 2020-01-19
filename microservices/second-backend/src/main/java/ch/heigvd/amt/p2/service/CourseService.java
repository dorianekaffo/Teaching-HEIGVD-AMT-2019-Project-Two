package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.api.dto.CourseDto;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.model.Student;
import ch.heigvd.amt.p2.repository.CourseRepository;
import ch.heigvd.amt.p2.repository.EnrollmentRepository;
import ch.heigvd.amt.p2.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
public class CourseService implements IEntityService<Course, Long> {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Override
    public Course create(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course entity) throws ResourceNotFoundException {
        if (this.courseRepository.existsById(id) && entity.getId() != null) {
            return this.courseRepository.save(entity);
        }
        throw new ResourceNotFoundException();
    }

    @Transactional
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (this.courseRepository.existsById(id)) {
            this.enrollmentService.deleteByCourse(id);
            this.courseRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Course get(Long id) throws ResourceNotFoundException {
        return this.courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException()
        );
    }

    @Override
    public Page<Course> getAll(Pageable pgble) {
        return this.courseRepository.findAll(pgble);
    }

    @Override
    public long count() {
        return this.courseRepository.count();
    }

    public CourseDto convertToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        if (course.getId() != null) {
        courseDto.setId(course.getId().intValue());}
        if (course.getTitle() != null) {
        courseDto.setTitle(course.getTitle());}
        if (course.getOwner() != null) {
        courseDto.setOwner(course.getOwner());}
        return courseDto;
    }

    public Course convertToEntity(CourseDto courseDto) {
        Course course = new Course();
        if (courseDto.getId() != null) {
            course.setId(courseDto.getId().longValue());
        }
        if (courseDto.getTitle() != null) {
            course.setTitle(courseDto.getTitle());
        }
        if (courseDto.getOwner() != null) {
            course.setOwner(courseDto.getOwner());
        }
        return course;
    }

    public Page<Course> getAllByOwner(String owner, Pageable pgble) {
        return this.courseRepository.findByOwner(owner, pgble);
    }

    public boolean isOwner(Long id, String owner) throws ResourceNotFoundException {
        if (!this.courseRepository.existsById(id)) throw new ResourceNotFoundException();
        return this.courseRepository.existsByIdAndOwner(id, owner);
    }
}
