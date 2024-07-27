package com.studentManagement.lms.service;

import com.studentManagement.lms.modal.Enrollment;
import com.studentManagement.lms.modal.Student;
import com.studentManagement.lms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentService studentService;


    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public List<Enrollment> getEnrollmentByStudentId(Long id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {
            return enrollmentRepository.findAllByStudent(student.get());
        } else {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        Enrollment existingEnrollment = enrollmentRepository.findById(id).orElse(null);
        if (existingEnrollment != null) {
            existingEnrollment.setStudent(enrollment.getStudent());
            existingEnrollment.setCourse(enrollment.getCourse());
            existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
            return enrollmentRepository.save(existingEnrollment);
        }
        return null;
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}