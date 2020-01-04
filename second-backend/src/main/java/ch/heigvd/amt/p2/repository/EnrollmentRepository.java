package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Course;
import ch.heigvd.amt.p2.model.Enrollment;
import ch.heigvd.amt.p2.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    @Query(value = "DELETE FROM Enrollment m WHERE m.course = ?1")
    void deletionByCourse(Course course);

    @Query(value = "DELETE FROM Enrollment m WHERE m.student = ?1")
    void deleteByStudentId(Student student);

}