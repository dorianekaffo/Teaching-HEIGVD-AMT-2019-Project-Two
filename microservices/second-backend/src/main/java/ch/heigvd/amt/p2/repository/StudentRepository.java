package ch.heigvd.amt.p2.repository;

import ch.heigvd.amt.p2.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

}