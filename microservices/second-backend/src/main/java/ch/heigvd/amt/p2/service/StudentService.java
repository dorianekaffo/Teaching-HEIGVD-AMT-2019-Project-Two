package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.api.dto.StudentDto;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.model.Course;
import ch.heigvd.amt.p2.model.Student;
import ch.heigvd.amt.p2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
public class StudentService implements IEntityService<Student, Long> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Override
    public Student create(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Student update(Long id, Student entity) throws ResourceNotFoundException {
        if (this.studentRepository.existsById(id) && entity.getId() != null) {
            return this.studentRepository.save(entity);
        }
        throw new ResourceNotFoundException();
    }

    @Transactional
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (this.studentRepository.existsById(id)) {
            this.enrollmentService.deleteByStudent(id);
            this.studentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Student get(Long id) throws ResourceNotFoundException {
        return this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public Page<Student> getAll(Pageable pgble) {
        return this.studentRepository.findAll(pgble);
    }

    @Override
    public long count() {
        return this.studentRepository.count();
    }

    public StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        if (student.getId() != null) {
            studentDto.setId(student.getId().intValue());
        }
        if (student.getFirstname() != null) {
            studentDto.setFirstname(student.getFirstname());
        }
        if (student.getLastname() != null) {
            studentDto.setLastname(student.getLastname());
        }
        if (student.getOwner() != null) {
            studentDto.setOwner(student.getOwner());
        }
        return studentDto;
    }

    public Student convertToEntity(StudentDto studentDto) {
        Student student = new Student();
        if (studentDto.getId() != null) {
            student.setId(studentDto.getId().longValue());
        }
        if (studentDto.getFirstname() != null) {
            student.setFirstname(studentDto.getFirstname());
        }
        if (studentDto.getLastname() != null) {
            student.setLastname(studentDto.getLastname());
        }
        if (studentDto.getOwner() != null) {
            student.setOwner(studentDto.getOwner());
        }
        return student;
    }

    public Page<Student> getAllByOwner(String owner, Pageable pgble) {
        return this.studentRepository.findByOwner(owner, pgble);
    }

    public boolean isOwner(Long id, String owner) throws ResourceNotFoundException {
        if (!this.studentRepository.existsById(id)) throw new ResourceNotFoundException();
        return this.studentRepository.existsByIdAndOwner(id, owner);
    }
}
