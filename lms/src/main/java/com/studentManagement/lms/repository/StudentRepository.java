package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Student;
import com.studentManagement.lms.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
    List<Student> findByAge(int age);

    Student findByUser(User user);
}
