package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
