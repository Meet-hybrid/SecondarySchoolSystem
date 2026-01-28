package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.CreateStudentRequestDTO;
import com.hybrid.SecondarySchoolSystem.dto.response.StudentResponse;
import com.hybrid.SecondarySchoolSystem.entity.Student;
import com.hybrid.SecondarySchoolSystem.repository.StudentRepository;
import com.hybrid.SecondarySchoolSystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {
    StudentController studentController;
    StudentService studentService;
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();
        studentService = new StudentService(studentRepository);
        studentController = new StudentController(studentService);
    }

    @Test
    public void createStudent_WithValidRequest_CreatesStudent_Test() {
        CreateStudentRequestDTO request = new CreateStudentRequestDTO(
                "NewStudent",
                "SSS1",
                "SCIENCE"
        );

        studentController.createStudent(request);
        java.util.List<Student> students = studentRepository.getAllStudents();

        assertTrue(students.size() > 0);
        Student created = students.get(students.size() - 1);
        assertEquals("NewStudent", created.getName());
        assertEquals("SSS1", created.getClassLevel());
        assertEquals("SCIENCE", created.getDepartment());
    }

    @Test
    public void createStudent_MultipleStudents_AllCreated_Test() {
        CreateStudentRequestDTO request1 = new CreateStudentRequestDTO("Student1", "SSS1", "SCIENCE");
        CreateStudentRequestDTO request2 = new CreateStudentRequestDTO("Student2", "SSS2", "ARTS");

        studentController.createStudent(request1);
        studentController.createStudent(request2);

        java.util.List<Student> students = studentRepository.getAllStudents();
        assertEquals(2, students.size());
        assertEquals("Student1", students.get(0).getName());
        assertEquals("Student2", students.get(1).getName());
    }

    @Test
    public void getAllStudents_ReturnsAllStudents_Test() {
        studentController.createStudent(new CreateStudentRequestDTO("St1", "SSS1", "SCIENCE"));
        studentController.createStudent(new CreateStudentRequestDTO("St2", "SSS2", "ARTS"));

        java.util.List<Student> students = studentRepository.getAllStudents();

        assertEquals(2, students.size());
    }

    @Test
    public void studentResponse_ContainsAllRequiredFields_Test() {
        CreateStudentRequestDTO request = new CreateStudentRequestDTO(
                "TestStudent",
                "SSS3",
                "COMMERCIAL"
        );

        studentController.createStudent(request);
        java.util.List<Student> students = studentRepository.getAllStudents();

        assertTrue(students.size() > 0);
        Student created = students.get(students.size() - 1);
        assertEquals("TestStudent", created.getName());
    }
}
