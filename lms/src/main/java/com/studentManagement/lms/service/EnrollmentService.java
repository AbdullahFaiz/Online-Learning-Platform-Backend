package com.studentManagement.lms.service;

import com.studentManagement.lms.modal.Enrollment;
import com.studentManagement.lms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
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