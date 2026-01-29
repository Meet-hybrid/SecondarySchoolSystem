package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Student;
import com.hybrid.SecondarySchoolSystem.exceptions.DuplicateStudentException;
import com.hybrid.SecondarySchoolSystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    StudentService studentService;
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        // Create a fresh repository for each test to avoid shared state
        studentRepository = new StudentRepository();
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void adminCan_CreateStudent_Test() {
        Student student = studentService.createStudent(
                "Ajebo Dev",
                "SSS3",
                "SCIENCE"
        );

        assertNotNull(student);
        assertEquals("Ajebo Dev", student.getName());
        assertEquals("SSS3", student.getLastName());
        assertEquals("SCIENCE", student.getDepartment());
    }

    @Test
    public void savedStudent_CanBeRetrieved_Test() {
        studentService.createStudent("Ajebo Dev", "SSS3", "SCIENCE");
        assertEquals(1, studentRepository.getAllStudents().size());
    }

    @Test
    public void adminCannot_CreateDuplicateStudent_Test() {
        studentService.createStudent("Ajebo Dev", "SSS3", "SCIENCE");

        Exception exception = assertThrows(DuplicateStudentException.class, () -> 
            studentService.createStudent("Ajebo Dev", "SSS3", "SCIENCE")
        );

        assertEquals("Student already exists", exception.getMessage());
    }

    @Test
    public void createdStudent_HasValidId_Test() {
        Student student = studentService.createStudent("Hybrid Tech", "SSS2", "SCIENCE");
        
        assertNotNull(student.getId());
        assertFalse(student.getId().isEmpty());
    }

    @Test
    public void getAllStudents_ReturnsEmptyList_WhenNoStudents_Test() {
        List<Student> students = studentRepository.getAllStudents();
        
        assertNotNull(students);
        assertEquals(0, students.size());
    }

    @Test
    public void getAllStudents_ReturnsMultipleStudents_Test() {
        studentService.createStudent("Student 1", "SSS1", "SCIENCE");
        studentService.createStudent("Student 2", "SSS2", "ARTS");
        studentService.createStudent("Student 3", "SSS3", "COMMERCIAL");
        
        List<Student> students = studentRepository.getAllStudents();
        
        assertEquals(3, students.size());
    }

    @Test
    public void findByName_ReturnsCorrectStudent_Test() {
        studentService.createStudent("Leena", "SSS1", "SCIENCE");
        studentService.createStudent("Fimi", "SSS2", "ARTS");
        
        Student found = studentRepository.findByName("Leena").orElse(null);
        
        assertNotNull(found);
        assertEquals("Leena", found.getName());
        assertEquals("SCIENCE", found.getDepartment());
    }

    @Test
    public void findByName_ReturnsEmpty_WhenNotFound_Test() {
        studentService.createStudent("Leena", "SSS1", "SCIENCE");
        
        Optional<Student> found = studentRepository.findByName("NonExistent");
        
        assertTrue(found.isEmpty());
    }

    @Test
    public void findById_ReturnsCorrectStudent_Test() {
        Student created = studentService.createStudent("Charlie", "SSS3", "COMMERCIAL");
        
        // Find all and check
        List<Student> all = studentRepository.getAllStudents();
        Student found = all.stream().filter(s -> s.getId().equals(created.getId())).findFirst().orElse(null);
        
        assertNotNull(found);
        assertEquals("Charlie", found.getName());
        assertEquals(created.getId(), found.getId());
    }

    @Test
    public void findById_ReturnsNull_WhenNotFound_Test() {
        List<Student> all = studentRepository.getAllStudents();
        Student found = all.stream().filter(s -> s.getId().equals("invalid-id-123")).findFirst().orElse(null);
        
        assertNull(found);
    }

    @Test
    public void createStudent_WithDifferentDepartments_Test() {
        Student science = studentService.createStudent("ScienceStudent", "SSS1", "SCIENCE");
        Student arts = studentService.createStudent("ArtsStudent", "SSS1", "ARTS");
        Student commercial = studentService.createStudent("CommStudent", "SSS1", "COMMERCIAL");
        
        assertEquals("SCIENCE", science.getDepartment());
        assertEquals("ARTS", arts.getDepartment());
        assertEquals("COMMERCIAL", commercial.getDepartment());
    }

    @Test
    public void createStudent_WithDifferentClassLevels_Test() {
        Student sss1 = studentService.createStudent("Student1", "SSS1", "SCIENCE");
        Student sss2 = studentService.createStudent("Student2", "SSS2", "SCIENCE");
        Student sss3 = studentService.createStudent("Student3", "SSS3", "SCIENCE");
        
        assertEquals("SSS1", sss1.getLastName());
        assertEquals("SSS2", sss2.getLastName());
        assertEquals("SSS3", sss3.getLastName());
    }

    @Test
    public void save_AddsStudentToRepository_Test() {
        Student student = new Student("id1", "TestName", "SSS1", "SCIENCE");
        
        studentRepository.save(student);
        
        assertEquals(1, studentRepository.getAllStudents().size());
        Student saved = studentRepository.getAllStudents().stream()
            .filter(s -> s.getId().equals("id1"))
            .findFirst()
            .orElse(null);
        assertEquals("TestName", saved.getName());
    }

    @Test
    public void studentHas_AllRequiredFields_Test() {
        Student student = studentService.createStudent("FullName", "SSS2", "ARTS");
        
        assertNotNull(student.getId());
        assertNotNull(student.getName());
        assertNotNull(student.getLastName());
        assertNotNull(student.getDepartment());
        assertEquals("FullName", student.getName());
        assertEquals("SSS2", student.getLastName());
        assertEquals("ARTS", student.getDepartment());
    }

    @Test
    public void createdStudentsCount_IncrementsCorrectly_Test() {
        int initial = studentRepository.getAllStudents().size();
        
        studentService.createStudent("Count1", "SSS1", "SCIENCE");
        studentService.createStudent("Count2", "SSS2", "ARTS");
        
        int after = studentRepository.getAllStudents().size();
        assertEquals(initial + 2, after);
    }
}