package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Role;
import com.studentManagement.lms.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User> findByRole(Role role);
}
