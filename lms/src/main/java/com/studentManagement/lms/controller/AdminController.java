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
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllAdmins() {
        return userService.getUsersByRole(Role.ROLE_ADMIN);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getAdminById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null && user.getRole() == Role.ROLE_ADMIN) {
            return user;
        }
        throw new RuntimeException("Admin not found or not authorized");
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createAdmin(@RequestBody User user) {
        user.setRole(Role.ROLE_ADMIN);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateAdmin(@PathVariable Long id, @RequestBody User userDetails) {
        userDetails.setRole(Role.ROLE_ADMIN);
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAdmin(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null && user.getRole() == Role.ROLE_ADMIN) {
            userService.deleteUser(id);
        } else {
            throw new RuntimeException("Admin not found or not authorized");
        }
    }

    @GetMapping("/createAdmin")
    public String initaiteAdminCredtentials() {
        User admin = new User("admin","Ab@12345","admin@gmail.com",Role.ROLE_ADMIN);
        if (!userService.existByUsername(admin.getUsername())){
            this.userService.createUser(admin);
            return "Default Admin Created Successfully";
        }
        return "Default Admin Already Exists";

    }


}