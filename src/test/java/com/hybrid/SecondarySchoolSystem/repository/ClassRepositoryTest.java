package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.SchoolClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ClassRepositoryTest {

    private ClassRepository classRepository;

    @BeforeEach
    void setUp() {
        classRepository = new ClassRepository();
    }

    @Test
    public void testSaveClass() {
        SchoolClass schoolClass = new SchoolClass("SS1A", "SS1", "T001");
        SchoolClass savedClass = classRepository.save(schoolClass);

        assertNotNull(savedClass);
        assertNotNull(savedClass.getId());
        assertEquals("SS1A", savedClass.getClassName());
        assertEquals("SS1", savedClass.getLevel());
    }

    @Test
    public void testFindAll() {
        SchoolClass class1 = new SchoolClass("SS1A", "SS1", "T001");
        SchoolClass class2 = new SchoolClass("SS2B", "SS2", "T002");

        classRepository.save(class1);
        classRepository.save(class2);

        List<SchoolClass> allClasses = classRepository.findAll();

        assertNotNull(allClasses);
        assertEquals(2, allClasses.size());
    }

    @Test
    public void testFindById() {
        SchoolClass schoolClass = new SchoolClass("SS1A", "SS1", "T001");
        SchoolClass savedClass = classRepository.save(schoolClass);

        Optional<SchoolClass> foundClass = classRepository.findById(savedClass.getId());

        assertTrue(foundClass.isPresent());
        assertEquals(savedClass.getId(), foundClass.get().getId());
        assertEquals("SS1A", foundClass.get().getClassName());
    }

    @Test
    public void testFindByClassName() {
        SchoolClass schoolClass = new SchoolClass("SS1A", "SS1", "T001");
        classRepository.save(schoolClass);

        Optional<SchoolClass> foundClass = classRepository.findByClassName("SS1A");

        assertTrue(foundClass.isPresent());
        assertEquals("SS1A", foundClass.get().getClassName());
    }

    @Test
    public void testEmptyRepository() {
        List<SchoolClass> allClasses = classRepository.findAll();

        assertNotNull(allClasses);
        assertEquals(0, allClasses.size());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<SchoolClass> foundClass = classRepository.findById("NONEXISTENT");

        assertFalse(foundClass.isPresent());
    }

    @Test
    public void testFindByClassNameNotFound() {
        Optional<SchoolClass> foundClass = classRepository.findByClassName("NONEXISTENT");

        assertFalse(foundClass.isPresent());
    }

    @Test
    public void testMultipleSavesWithDifferentLevels() {
        SchoolClass ss1 = new SchoolClass("SS1A", "SS1", "T001");
        SchoolClass ss2 = new SchoolClass("SS2B", "SS2", "T002");
        SchoolClass ss3 = new SchoolClass("SS3C", "SS3", "T003");

        classRepository.save(ss1);
        classRepository.save(ss2);
        classRepository.save(ss3);

        List<SchoolClass> allClasses = classRepository.findAll();

        assertEquals(3, allClasses.size());
    }

    @Test
    public void testGetClassTeacherId() {
        SchoolClass schoolClass = new SchoolClass("SS1A", "SS1", "TEACHER123");
        SchoolClass savedClass = classRepository.save(schoolClass);

        Optional<SchoolClass> foundClass = classRepository.findById(savedClass.getId());

        assertTrue(foundClass.isPresent());
        assertEquals("TEACHER123", foundClass.get().getClassTeacherId());
    }
}
