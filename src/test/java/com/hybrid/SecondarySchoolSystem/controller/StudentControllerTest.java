package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.CreateStudentRequestDTO;
import com.hybrid.SecondarySchoolSystem.entity.Student;
import com.hybrid.SecondarySchoolSystem.repository.StudentRepository;
import com.hybrid.SecondarySchoolSystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        CreateStudentRequestDTO request = new CreateStudentRequestDTO();
        request.setFirstName("New");
        request.setLastName("Student");
        request.setRegistrationNumber("REG001");
        request.setLevel("SSS1");
        request.setClassId("SCIENCE_CLASS");
        request.setParentEmail("parent@email.com");

        studentController.createStudent(request);
        List<Student> students = studentRepository.findAll();

        assertTrue(students.size() > 0);
        Student created = students.get(students.size() - 1);
        assertEquals("New Student", created.getName());
        assertEquals("SSS1", created.getClassLevel());
        assertEquals("SCIENCE_CLASS", created.getClassId());
        assertEquals("REG001", created.getRegistrationNumber());
    }

    @Test
    public void createStudent_MultipleStudents_AllCreated_Test() {
        CreateStudentRequestDTO request1 = new CreateStudentRequestDTO();
        request1.setFirstName("Student");
        request1.setLastName("One");
        request1.setRegistrationNumber("REG001");
        request1.setLevel("SSS1");
        request1.setClassId("SCIENCE_CLASS");
        request1.setParentEmail("parent1@email.com");

        CreateStudentRequestDTO request2 = new CreateStudentRequestDTO();
        request2.setFirstName("Student");
        request2.setLastName("Two");
        request2.setRegistrationNumber("REG002");
        request2.setLevel("SSS2");
        request2.setClassId("ARTS_CLASS");
        request2.setParentEmail("parent2@email.com");

        studentController.createStudent(request1);
        studentController.createStudent(request2);

        List<Student> students = studentRepository.findAll();
        assertEquals(2, students.size());
        assertEquals("Student One", students.get(0).getName());
        assertEquals("Student Two", students.get(1).getName());
    }

    @Test
    public void getAllStudents_ReturnsAllStudents_Test() {
        CreateStudentRequestDTO request1 = new CreateStudentRequestDTO();
        request1.setFirstName("St");
        request1.setLastName("One");
        request1.setRegistrationNumber("REG001");
        request1.setLevel("SSS1");
        request1.setClassId("SCIENCE_CLASS");
        request1.setParentEmail("st1@email.com");

        CreateStudentRequestDTO request2 = new CreateStudentRequestDTO();
        request2.setFirstName("St");
        request2.setLastName("Two");
        request2.setRegistrationNumber("REG002");
        request2.setLevel("SSS2");
        request2.setClassId("ARTS_CLASS");
        request2.setParentEmail("st2@email.com");

        studentController.createStudent(request1);
        studentController.createStudent(request2);

        List<Student> students = studentRepository.findAll();

        assertEquals(2, students.size());
    }

    @Test
    public void studentResponse_ContainsAllRequiredFields_Test() {
        CreateStudentRequestDTO request = new CreateStudentRequestDTO();
        request.setFirstName("Test");
        request.setLastName("Student");
        request.setRegistrationNumber("REG003");
        request.setLevel("SSS3");
        request.setClassId("COMMERCIAL_CLASS");
        request.setParentEmail("test@email.com");

        studentController.createStudent(request);
        List<Student> students = studentRepository.findAll();

        assertTrue(students.size() > 0);
        Student created = students.get(students.size() - 1);
        assertEquals("Test Student", created.getName());
        assertEquals("SSS3", created.getClassLevel());
        assertEquals("COMMERCIAL_CLASS", created.getClassId());
        assertEquals("REG003", created.getRegistrationNumber());
        assertEquals("test@email.com", created.getParentEmail());
    }

    @Test
    public void createStudent_WithUniqueRegistrationNumbers_Test() {
        CreateStudentRequestDTO request1 = new CreateStudentRequestDTO();
        request1.setFirstName("John");
        request1.setLastName("Doe");
        request1.setRegistrationNumber("REG100");
        request1.setLevel("SSS1");
        request1.setClassId("CLASS01");
        request1.setParentEmail("john@email.com");

        CreateStudentRequestDTO request2 = new CreateStudentRequestDTO();
        request2.setFirstName("Jane");
        request2.setLastName("Doe");
        request2.setRegistrationNumber("REG101");
        request2.setLevel("SSS1");
        request2.setClassId("CLASS01");
        request2.setParentEmail("jane@email.com");

        studentController.createStudent(request1);
        studentController.createStudent(request2);

        List<Student> students = studentRepository.findAll();
        assertEquals(2, students.size());

        Student student1 = studentRepository.findByRegistrationNumber("REG100").orElse(null);
        Student student2 = studentRepository.findByRegistrationNumber("REG101").orElse(null);

        assertNotNull(student1);
        assertNotNull(student2);
        assertEquals("John Doe", student1.getName());
        assertEquals("Jane Doe", student2.getName());
    }
}