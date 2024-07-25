package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
