package com.studentManagement.lms.service;

import com.studentManagement.lms.modal.Student;
import com.studentManagement.lms.modal.User;
import com.studentManagement.lms.repository.StudentRepository;
import com.studentManagement.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student getStudentByUser(User user) {
        return studentRepository.findByUser(user);
    }
    @Transactional
    public Student createStudent(Student student) {
        // Check if user already exists by username
        Optional<User> existingUserByUsername = Optional.ofNullable(userRepository.findByUsername(student.getUser().getUsername()));
        if (existingUserByUsername.isPresent()) {
            throw new RuntimeException("User with the given username already exists");
        }

        // Create and save the user if it does not exist
        User user = student.getUser();
        userService.createUser(user);

        student.setUser(user);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        studentDetails.getUser().setId(student.getUser().getId());


        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.getUser().setUsername(studentDetails.getUser().getUsername());
        student.getUser().setPassword(studentDetails.getUser().getPassword());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        studentRepository.delete(student);
        userRepository.delete(student.getUser());
    }
}