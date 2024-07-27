package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Enrollment;
import com.studentManagement.lms.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByStudent(Student student);
}
