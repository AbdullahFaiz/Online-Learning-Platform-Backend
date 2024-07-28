package com.studentManagement.lms.controller;

import com.studentManagement.lms.modal.Role;
import com.studentManagement.lms.modal.User;
import com.studentManagement.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllAdmins() {
        try {
            List<User> admins = userService.getUsersByRole(Role.ROLE_ADMIN);
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getAdminById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user != null && user.getRole() == Role.ROLE_ADMIN) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        try {
            user.setRole(Role.ROLE_ADMIN);
            User newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateAdmin(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            userDetails.setRole(Role.ROLE_ADMIN);
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user != null && user.getRole() == Role.ROLE_ADMIN) {
                userService.deleteUser(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/createAdmin")
    public ResponseEntity<String> initaiteAdminCredtentials() {
        try {
            User admin = new User("admin","Ab@12345","admin@gmail.com",Role.ROLE_ADMIN);
            if (!userService.existByUsername(admin.getUsername())){
                userService.createUser(admin);
                return new ResponseEntity<>("Default Admin Created Successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Default Admin Already Exists", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}