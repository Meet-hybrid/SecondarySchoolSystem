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
        CreateStudentRequestDTO dto = new CreateStudentRequestDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setRegistrationNumber("REG001");
        dto.setLevel("SS1");
        dto.setClassId("Class1");
        dto.setParentEmail("parent@email.com");

        StudentResponse response = studentController.createStudent(dto);

        assertNotNull(response);
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("REG001", response.getRegistrationNumber());
        assertEquals("SS1", response.getLevel());
    }

    @Test
    public void testCreateMultipleStudents() {
        CreateStudentRequestDTO dto1 = new CreateStudentRequestDTO();
        dto1.setFirstName("Alice");
        dto1.setLastName("Smith");
        dto1.setRegistrationNumber("REG002");
        dto1.setLevel("SS2");
        dto1.setClassId("Class2");
        dto1.setParentEmail("alice@email.com");

        CreateStudentRequestDTO dto2 = new CreateStudentRequestDTO();
        dto2.setFirstName("Bob");
        dto2.setLastName("Johnson");
        dto2.setRegistrationNumber("REG003");
        dto2.setLevel("SS3");
        dto2.setClassId("Class3");
        dto2.setParentEmail("bob@email.com");

        StudentResponse response1 = studentController.createStudent(dto1);
        StudentResponse response2 = studentController.createStudent(dto2);

        assertNotNull(response1);
        assertNotNull(response2);
        assertNotEquals(response1.getId(), response2.getId());
    }

    @Test
    public void testGetAllStudents() {
        CreateStudentRequestDTO dto1 = new CreateStudentRequestDTO();
        dto1.setFirstName("Charlie");
        dto1.setLastName("Brown");
        dto1.setRegistrationNumber("REG004");
        dto1.setLevel("SS1");
        dto1.setClassId("Class1");
        dto1.setParentEmail("charlie@email.com");

        studentController.createStudent(dto1);

        List<StudentResponse> students = studentController.getAllStudents();

        assertNotNull(students);
        assertTrue(students.size() >= 1);
    }

    @Test
    public void testDuplicateStudentThrowsException() {
        CreateStudentRequestDTO dto = new CreateStudentRequestDTO();
        dto.setFirstName("David");
        dto.setLastName("Miller");
        dto.setRegistrationNumber("REG005");
        dto.setLevel("SS2");
        dto.setClassId("Class2");
        dto.setParentEmail("david@email.com");

        studentController.createStudent(dto);

        CreateStudentRequestDTO duplicateDto = new CreateStudentRequestDTO();
        duplicateDto.setFirstName("David");
        duplicateDto.setLastName("Miller");
        duplicateDto.setRegistrationNumber("REG005");
        duplicateDto.setLevel("SS2");
        duplicateDto.setClassId("Class2");
        duplicateDto.setParentEmail("david2@email.com");

        assertThrows(DuplicateStudentException.class, () -> {
            studentController.createStudent(duplicateDto);
        });
    }

    @Test
    public void testStudentWithDifferentLevels() {
        CreateStudentRequestDTO[] dtos = new CreateStudentRequestDTO[3];
        String[] levels = {"SS1", "SS2", "SS3"};
        
        for (int count = 0; count < 3; count++) {
            dtos[count] = new CreateStudentRequestDTO();
            dtos[count].setFirstName("Student" + count);
            dtos[count].setLastName("Level" + count);
            dtos[count].setRegistrationNumber("REG00" + (6 + count));
            dtos[count].setLevel(levels[count]);
            dtos[count].setClassId("Class" + (count + 1));
            dtos[count].setParentEmail("student" + count + "@email.com");
        }

        for (CreateStudentRequestDTO dto : dtos) {
            StudentResponse response = studentController.createStudent(dto);
            assertNotNull(response);
        }
    }
}
