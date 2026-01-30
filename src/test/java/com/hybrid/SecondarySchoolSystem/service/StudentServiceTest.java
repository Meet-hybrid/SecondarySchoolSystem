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
                "Ajebo",
                "Dev",
                "REG001",
                "SSS3",
                "CLASS01",
                "parent@email.com"
        );

        assertNotNull(student);
        assertEquals("Ajebo Dev", student.getName());
        assertEquals("SSS3", student.getClassLevel());
        assertEquals("REG001", student.getRegistrationNumber());
        assertEquals("parent@email.com", student.getParentEmail());
    }

    @Test
    public void savedStudent_CanBeRetrieved_Test() {
        studentService.createStudent("Ajebo", "Dev", "REG001", "SSS3", "CLASS01", "parent@email.com");
        assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    public void adminCannot_CreateDuplicateStudent_Test() {
        studentService.createStudent("Ajebo", "Dev", "REG001", "SSS3", "CLASS01", "parent@email.com");

        Exception exception = assertThrows(DuplicateStudentException.class, () ->
                studentService.createStudent("Different", "Name", "REG001", "SSS2", "CLASS02", "other@email.com")
        );

        assertTrue(exception.getMessage().contains("REG001"));
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    public void createdStudent_HasValidId_Test() {
        Student student = studentService.createStudent("Hybrid", "Tech", "REG002", "SSS2", "CLASS01", "parent2@email.com");

        assertNotNull(student.getId());
        assertFalse(student.getId().isEmpty());
    }

    @Test
    public void getAllStudents_ReturnsEmptyList_WhenNoStudents_Test() {
        List<Student> students = studentRepository.findAll();

        assertNotNull(students);
        assertEquals(0, students.size());
    }

    @Test
    public void getAllStudents_ReturnsMultipleStudents_Test() {
        studentService.createStudent("Student", "One", "REG001", "SSS1", "CLASS01", "parent1@email.com");
        studentService.createStudent("Student", "Two", "REG002", "SSS2", "CLASS02", "parent2@email.com");
        studentService.createStudent("Student", "Three", "REG003", "SSS3", "CLASS03", "parent3@email.com");

        List<Student> students = studentRepository.findAll();

        assertEquals(3, students.size());
    }

    @Test
    public void findByName_ReturnsCorrectStudent_Test() {
        studentService.createStudent("Leena", "Smith", "REG004", "SSS1", "CLASS01", "leena@email.com");
        studentService.createStudent("Fimi", "Jones", "REG005", "SSS2", "CLASS02", "fimi@email.com");

        Student found = studentRepository.findByName("Leena Smith").orElse(null);

        assertNotNull(found);
        assertEquals("Leena Smith", found.getName());
        assertEquals("SSS1", found.getClassLevel());
    }

    @Test
    public void findByName_ReturnsEmpty_WhenNotFound_Test() {
        studentService.createStudent("Leena", "Smith", "REG006", "SSS1", "CLASS01", "leena@email.com");

        Optional<Student> found = studentRepository.findByName("NonExistent");

        assertTrue(found.isEmpty());
    }

    @Test
    public void findById_ReturnsCorrectStudent_Test() {
        Student created = studentService.createStudent("Charlie", "Brown", "REG007", "SSS3", "CLASS03", "charlie@email.com");

        Student found = studentRepository.findById(created.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Charlie Brown", found.getName());
        assertEquals(created.getId(), found.getId());
    }

    @Test
    public void findById_ReturnsEmpty_WhenNotFound_Test() {
        Optional<Student> found = studentRepository.findById("invalid-id-123");

        assertTrue(found.isEmpty());
    }

    @Test
    public void createStudent_WithDifferentClassIds_Test() {
        Student science = studentService.createStudent("Science", "Student", "REG008", "SSS1", "SCIENCE_CLASS", "sci@email.com");
        Student arts = studentService.createStudent("Arts", "Student", "REG009", "SSS1", "ARTS_CLASS", "arts@email.com");
        Student commercial = studentService.createStudent("Comm", "Student", "REG010", "SSS1", "COMM_CLASS", "comm@email.com");

        assertEquals("SCIENCE_CLASS", science.getClassId());
        assertEquals("ARTS_CLASS", arts.getClassId());
        assertEquals("COMM_CLASS", commercial.getClassId());
    }

    @Test
    public void createStudent_WithDifferentClassLevels_Test() {
        Student sssOne = studentService.createStudent("Student", "One", "REG011", "SSS1", "CLASS01", "s1@email.com");
        Student sssTwo = studentService.createStudent("Student", "Two", "REG012", "SSS2", "CLASS01", "s2@email.com");
        Student sssThree = studentService.createStudent("Student", "Three", "REG013", "SSS3", "CLASS01", "s3@email.com");

        assertEquals("SSS1", sssOne.getClassLevel());
        assertEquals("SSS2", sssTwo.getClassLevel());
        assertEquals("SSS3", sssThree.getClassLevel());
    }

    @Test
    public void save_AddsStudentToRepository_Test() {
        Student student = new Student("id1", "Test Name", "REG014", "SSS1", "CLASS01", "test@email.com");

        studentRepository.save(student);

        assertEquals(1, studentRepository.findAll().size());
        Student saved = studentRepository.findById("id1").orElse(null);
        assertNotNull(saved);
        assertEquals("Test Name", saved.getName());
    }

    @Test
    public void studentHas_AllRequiredFields_Test() {
        Student student = studentService.createStudent("Full", "Name", "REG015", "SSS2", "CLASS02", "full@email.com");

        assertNotNull(student.getId());
        assertNotNull(student.getName());
        assertNotNull(student.getRegistrationNumber());
        assertNotNull(student.getClassLevel());
        assertNotNull(student.getClassId());
        assertNotNull(student.getParentEmail());
        assertEquals("Full Name", student.getName());
        assertEquals("REG015", student.getRegistrationNumber());
        assertEquals("SSS2", student.getClassLevel());
        assertEquals("full@email.com", student.getParentEmail());
    }

    @Test
    public void createdStudentsCount_IncrementsCorrectly_Test() {
        int initial = studentRepository.findAll().size();

        studentService.createStudent("Count", "One", "REG016", "SSS1", "CLASS01", "count1@email.com");
        studentService.createStudent("Count", "Two", "REG017", "SSS2", "CLASS02", "count2@email.com");

        int after = studentRepository.findAll().size();
        assertEquals(initial + 2, after);
    }

    @Test
    public void findByRegistrationNumber_ReturnsCorrectStudent_Test() {
        studentService.createStudent("John", "Doe", "REG018", "SSS1", "CLASS01", "john@email.com");

        Student found = studentRepository.findByRegistrationNumber("REG018").orElse(null);

        assertNotNull(found);
        assertEquals("John Doe", found.getName());
        assertEquals("REG018", found.getRegistrationNumber());
    }

    @Test
    public void findByRegistrationNumber_ReturnsEmpty_WhenNotFound_Test() {
        Optional<Student> found = studentRepository.findByRegistrationNumber("NONEXISTENT");

        assertTrue(found.isEmpty());
    }
}