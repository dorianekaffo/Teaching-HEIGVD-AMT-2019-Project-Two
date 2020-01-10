package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.model.Enrollment;
import ch.heigvd.amt.p2.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService implements IEntityService<Enrollment, Long> {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment create(Enrollment entity) {
        return null;
    }

    @Override
    public Enrollment update(Long id, Enrollment entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Enrollment get(Long id) {
        return null;
    }

    @Override
    public Page<Enrollment> getAll(Pageable pgble) {
        return null;
    }

    @Override
    public long count() {
        return this.enrollmentRepository.count();
    }
}
