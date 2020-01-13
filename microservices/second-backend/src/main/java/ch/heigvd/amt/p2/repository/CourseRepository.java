package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    Page<Course> findByOwner(String owner, Pageable pgble);
    boolean existsByIdAndOwner(Long id, String owner);

}
