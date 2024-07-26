package com.studentManagement.lms.controller;

import com.studentManagement.lms.modal.Role;
import com.studentManagement.lms.modal.User;
import com.studentManagement.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllStudents() {
        return userService.getUsersByRole(Role.ROLE_STUDENT);
    }

    @GetMapping("/{id}")
    public User getStudentById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null && user.getRole() == Role.ROLE_STUDENT) {
            return user;
        }
        throw new RuntimeException("Student not found");
    }

    @PostMapping
    public User createStudent(@RequestBody User user) {
        user.setRole(Role.ROLE_STUDENT);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateStudent(@PathVariable Long id, @RequestBody User userDetails) {
        userDetails.setRole(Role.ROLE_STUDENT);
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null && user.getRole() == Role.ROLE_STUDENT) {
            userService.deleteUser(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }


}
