package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.model.Student;
import ch.heigvd.amt.p2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IEntityService<Student, Long> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Student update(Long id, Student entity) {
        if (this.studentRepository.existsById(id) && entity.getId() != null) {
            return this.studentRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Student get(Long id) {
        return this.studentRepository.findById(id).get();
    }

    @Override
    public Page<Student> getAll(Pageable pgble) {
        return this.studentRepository.findAll(pgble);
    }

    @Override
    public long count() {
        return this.studentRepository.count();
    }
}
