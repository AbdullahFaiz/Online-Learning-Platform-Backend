**Project Overview:**

**Online Learning Platform**: A web application for students to enroll in courses and for administrators to manage courses, students, and enrollments.

Technology Stack:
Backend: Spring Boot, MySQL
Frontend: Angular
API Documentation: Swagger

Key Features:
User Authentication and Authorization
Course Management (CRUD operations)
Student Management (CRUD operations)
Enrollment Management (CRUD operations)
Student Portal (Course listing, enrollment)
Admin Panel (Course, student, enrollment management)
Designing the System
Backend (Spring Boot)



Security:
Implement JWT-based authentication and authorization.

Models:
User (with roles: student, admin)
Course (id, title, description, etc.)
Student (id, name, email, etc.)
Enrollment (id, student, course, enrollmentDate)

Repositories:
UserRepository
CourseRepository
StudentRepository
EnrollmentRepository

Services:
UserService
CourseService
StudentService
EnrollmentService

Controllers:
AuthController (for login, registration)
StudentController (for student-related endpoints)
CourseController (for course-related endpoints)
EnrollmentController (for enrollment-related endpoints)
AdminController (for admin-specific endpoints)
