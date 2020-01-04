package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.repository.CourseRepository;
import ch.heigvd.amt.p2.repository.EnrollmentRepository;
import ch.heigvd.amt.p2.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseService implements IEntityService<Course, Long> {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Course create(Course entity) {
        return this.courseRepository.save(entity);
    }

    @Override
    public Course update(Long id, Course entity) {
        if (this.courseRepository.existsById(id) && entity.getId() != null) {
            return this.courseRepository.save(entity);
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Course course = this.courseRepository.findById(id).get();
        this.enrollmentRepository.deletionByCourse(course);
                this.courseRepository.deleteById(id);
    }

    @Override
    public Course get(Long id) {
        return this.courseRepository.findById(id).get();
    }

    @Override
    public Page<Course> getAll(Pageable pgble) {
        return this.courseRepository.findAll(pgble);
    }

    @Override
    public long count() {
        return this.courseRepository.count();
    }
}
