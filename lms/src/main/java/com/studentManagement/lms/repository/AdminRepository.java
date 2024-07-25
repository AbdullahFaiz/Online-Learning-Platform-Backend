package com.studentManagement.lms.repository;

import com.studentManagement.lms.modal.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
