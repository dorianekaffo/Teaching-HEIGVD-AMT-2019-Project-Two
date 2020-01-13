package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Course;
import ch.heigvd.amt.p2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    Page<Student> findByOwner(String owner, Pageable pgble);
    boolean existsByIdAndOwner(Long id, String owner);
}