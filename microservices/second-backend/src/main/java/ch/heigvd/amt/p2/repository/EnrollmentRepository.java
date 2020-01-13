package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Course;
import ch.heigvd.amt.p2.model.Enrollment;
import ch.heigvd.amt.p2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    void deleteByCourseId(Long courseId);

    void deleteByStudentId(Long studentId);

    Page<Enrollment> findByOwner(String owner, Pageable pgble);
    boolean existsByIdAndOwner(Long id, String owner);

}