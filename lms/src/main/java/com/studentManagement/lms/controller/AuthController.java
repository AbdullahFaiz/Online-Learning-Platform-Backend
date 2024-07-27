package com.studentManagement.lms.controller;

import com.studentManagement.lms.dto.LoginResponse;
import com.studentManagement.lms.dto.LoginRequest;
import com.studentManagement.lms.modal.Student;
import com.studentManagement.lms.repository.UserRepository;
import com.studentManagement.lms.service.AuthService;
import com.studentManagement.lms.service.StudentService;
import com.studentManagement.lms.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.studentManagement.lms.modal.User;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        User newUser = new User(user.getUsername(), encoder.encode(user.getPassword()), user.getEmail(),user.getRole());
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        final UserDetails userDetails = authService.loadUserByUsername(loginRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userRepository.findByUsername(loginRequest.getUsername());
        return ResponseEntity.ok(new LoginResponse(token,user));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // Fetch user details based on username
        User user = userRepository.findByUsername(username);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // Fetch user details based on username
        User user = userRepository.findByUsername(username);
        Student student = studentService.getStudentByUser(user);
        return ResponseEntity.ok(student);
    }
}