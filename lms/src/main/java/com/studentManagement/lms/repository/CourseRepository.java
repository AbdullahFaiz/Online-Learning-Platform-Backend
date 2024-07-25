package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
