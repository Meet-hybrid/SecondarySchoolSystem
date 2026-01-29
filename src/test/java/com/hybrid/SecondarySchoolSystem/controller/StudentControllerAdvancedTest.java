package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.CreateStudentRequestDTO;
import com.hybrid.SecondarySchoolSystem.dto.response.StudentResponse;
import com.hybrid.SecondarySchoolSystem.exceptions.DuplicateStudentException;
import com.hybrid.SecondarySchoolSystem.repository.StudentRepository;
import com.hybrid.SecondarySchoolSystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentControllerAdvancedTest {
    
    private StudentRepository studentRepository;
    private StudentService studentService;
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();
        studentService = new StudentService(studentRepository);
        studentController = new StudentController(studentService);
    }

    @Test
    public void testCreateStudentWithValidDetails() {
        CreateStudentRequestDTO newStudent = new CreateStudentRequestDTO();
        newStudent.setFirstName("John");
        newStudent.setLastName("Doe");
        newStudent.setRegistrationNumber("REG001");
        newStudent.setLevel("SS1");
        newStudent.setClassId("Class1");
        newStudent.setParentEmail("parent@email.com");

        StudentResponse response = new studentController.createStudent();
        assertNotNull(response);
        assertEquals("John", response.getFirstname);

        assertNotNull(response);
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("REG001", response.getRegistrationNumber());
        assertEquals("SS1", response.getLevel());
    }

    @Test
    public void testCreateMultipleStudents() {
        CreateStudentRequestDTO scienceStudent = new CreateStudentRequestDTO();
        scienceStudent.setFirstName("Alice");
        scienceStudent.setLastName("Smith");
        scienceStudent.setRegistrationNumber("REG002");
        scienceStudent.setLevel("SS2");
        scienceStudent.setClassId("Class2");
        scienceStudent.setParentEmail("alice@email.com");

        CreateStudentRequestDTO artsStudent = new CreateStudentRequestDTO();
        artsStudent.setFirstName("Bob");
        artsStudent.setLastName("Johnson");
        artsStudent.setRegistrationNumber("REG003");
        artsStudent.setLevel("SS3");
        artsStudent.setClassId("Class3");
        artsStudent.setParentEmail("bob@email.com");

        StudentResponse responseOne = studentController.createStudent(scienceStudent);
        StudentResponse responseTwo = studentController.createStudent(artsStudent);

        assertNotNull(responseOne);
        assertNotNull(responseTwo);
        assertNotEquals(responseOne.getId(), responseTwo.getId());
    }

    @Test
    public void testGetAllStudents() {
        CreateStudentRequestDTO registeredStudent = new CreateStudentRequestDTO();
        registeredStudent.setFirstName("Charlie");
        registeredStudent.setLastName("Brown");
        registeredStudent.setRegistrationNumber("REG004");
        registeredStudent.setLevel("SS1");
        registeredStudent.setClassId("Class1");
        registeredStudent.setParentEmail("charlie@email.com");

        studentController.createStudent(registeredStudent);

        List<StudentResponse> students = studentController.getAllStudents();

        assertNotNull(students);
        assertTrue(students.size() >= 1);
    }

    @Test
    public void testDuplicateStudentThrowsException() {
        CreateStudentRequestDTO initialRequest = new CreateStudentRequestDTO();
        initialRequest.setFirstName("David");
        initialRequest.setLastName("Miller");
        initialRequest.setRegistrationNumber("REG005");
        initialRequest.setLevel("SS2");
        initialRequest.setClassId("Class2");
        initialRequest.setParentEmail("david@email.com");

        studentController.createStudent(initialRequest);

        CreateStudentRequestDTO duplicateRequest = new CreateStudentRequestDTO();
        duplicateRequest.setFirstName("David");
        duplicateRequest.setLastName("Miller");
        duplicateRequest.setRegistrationNumber("REG005");
        duplicateRequest.setLevel("SS2");
        duplicateRequest.setClassId("Class2");
        duplicateRequest.setParentEmail("david2@email.com");

        assertThrows(DuplicateStudentException.class, () -> {
            studentController.createStudent(duplicateRequest);
        });
    }

    @Test
    public void testStudentWithDifferentLevels() {
        CreateStudentRequestDTO[] levelTestRequests = new CreateStudentRequestDTO[3];
        String[] levels = {"SS1", "SS2", "SS3"};
        
        for (int count = 0; count < 3; count++) {
            levelTestRequests[count] = new CreateStudentRequestDTO();
            levelTestRequests[count].setFirstName("Student" + count);
            levelTestRequests[count].setLastName("Level" + count);
            levelTestRequests[count].setRegistrationNumber("REG00" + (6 + count));
            levelTestRequests[count].setLevel(levels[count]);
            levelTestRequests[count].setClassId("Class" + (count + 1));
            levelTestRequests[count].setParentEmail("student" + count + "@email.com");
        }

        for (CreateStudentRequestDTO request : levelTestRequests) {
            StudentResponse response = studentController.createStudent(request);
            assertNotNull(response);
        }
    }
}
