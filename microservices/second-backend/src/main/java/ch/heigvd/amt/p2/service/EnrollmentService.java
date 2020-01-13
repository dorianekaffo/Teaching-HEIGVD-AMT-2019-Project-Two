package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.api.dto.EnrollmentDto;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Override
    public Enrollment create(Enrollment enrollment) {
        return this.enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment update(Long id, Enrollment entity) {
        return null;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (this.enrollmentRepository.existsById(id)) {
            this.enrollmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Enrollment get(Long id) throws ResourceNotFoundException {
        return this.enrollmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException());
    }

    @Override
    public Page<Enrollment> getAll(Pageable pgble) {
        return this.enrollmentRepository.findAll(pgble);
    }

    @Override
    public long count() {
        return this.enrollmentRepository.count();
    }

    public void deleteByCourse(Long idCourse) {
        this.enrollmentRepository.deleteByCourseId(idCourse);
    }

    public void deleteByStudent(Long idStudent) {
        this.enrollmentRepository.deleteByStudentId(idStudent);
    }

    public Page<Enrollment> getAllByOwner(String owner, Pageable pgble) {
        return this.enrollmentRepository.findByOwner(owner, pgble);
    }

    public boolean isOwner(Long id, String owner) {
        return this.enrollmentRepository.existsByIdAndOwner(id, owner);
    }

    public EnrollmentDto convertToDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        if (enrollment.getId() != null) {
            enrollmentDto.setId(enrollment.getId().intValue());
        }
        if (enrollment.getCourse() != null) {
            enrollmentDto.setCourse(enrollment.getCourse().getId().intValue());
        }
        if (enrollment.getStudent() != null) {
        enrollmentDto.setStudent(enrollment.getStudent().getId().intValue());}
        if (enrollment.getOwner() != null) {
        enrollmentDto.setOwner(enrollment.getOwner());}
        return enrollmentDto;
    }

    public Enrollment convertToEntity(EnrollmentDto enrollmentDto) throws ResourceNotFoundException {
        Enrollment enrollment = new Enrollment();
        if (enrollment.getId() != null) {
            enrollment.setId(enrollmentDto.getId().longValue());
        }
        enrollment.setCourse(this.courseService.get(enrollmentDto.getCourse().longValue()));
        enrollment.setStudent(this.studentService.get(enrollmentDto.getStudent().longValue()));
        enrollment.setOwner(enrollmentDto.getOwner());
        return enrollment;
    }

}
