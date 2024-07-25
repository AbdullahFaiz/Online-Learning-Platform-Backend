package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
