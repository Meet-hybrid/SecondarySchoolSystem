package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Result;
import com.hybrid.SecondarySchoolSystem.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultServiceTest {
    ResultService resultService;
    ResultRepository resultRepository;

    @BeforeEach
    void setUp() {
        resultRepository = new ResultRepository();
        resultService = new ResultService(resultRepository);
    }

    @Test
    public void uploadResult_Success_Test() {
        Result result = resultService.uploadResult("1", "Mathematics", 85.5, "A");

        assertNotNull(result);
        assertEquals("1", result.getStudentId());
        assertEquals("Mathematics", result.getSubject());
        assertEquals(85.5, result.getScore());
        assertEquals("A", result.getGrade());
    }

    @Test
    public void getStudentResults_Success_Test() {
        resultService.uploadResult("1", "Mathematics", 85.5, "A");
        resultService.uploadResult("1", "English", 78.0, "B");

        List<Result> results = resultService.getStudentResults("1");
        assertEquals(2, results.size());
    }

    @Test
    public void getResultByStudentAndSubject_Success_Test() {
        resultService.uploadResult("1", "Mathematics", 85.5, "A");

        Result result = resultService.getResultByStudentAndSubject("1", "Mathematics");
        assertNotNull(result);
        assertEquals("Mathematics", result.getSubject());
        assertEquals(85.5, result.getScore());
    }

    @Test
    public void getResultByStudentAndSubject_NotFound_Test() {
        Result result = resultService.getResultByStudentAndSubject("1", "NonExistent");
        assertNull(result);
    }

    @Test
    public void getAllResults_Success_Test() {
        resultService.uploadResult("1", "Mathematics", 85.5, "A");
        resultService.uploadResult("2", "English", 78.0, "B");

        List<Result> results = resultService.getAllResults();
        assertEquals(2, results.size());
    }

    @Test
    public void uploadResult_HasValidId_Test() {
        Result result = resultService.uploadResult("1", "Science", 90.0, "A");
        
        assertNotNull(result.getId());
        assertFalse(result.getId().isEmpty());
    }

    @Test
    public void getStudentResults_EmptyList_WhenNoResults_Test() {
        List<Result> results = resultService.getStudentResults("nonexistent-student");
        
        assertNotNull(results);
        assertEquals(0, results.size());
    }

    @Test
    public void uploadResult_WithDifferentGrades_Test() {
        Result gradeA = resultService.uploadResult("1", "Math", 90.0, "A");
        Result gradeB = resultService.uploadResult("2", "Math", 80.0, "B");
        Result gradeC = resultService.uploadResult("3", "Math", 70.0, "C");
        Result gradeF = resultService.uploadResult("4", "Math", 40.0, "F");
        
        assertEquals("A", gradeA.getGrade());
        assertEquals("B", gradeB.getGrade());
        assertEquals("C", gradeC.getGrade());
        assertEquals("F", gradeF.getGrade());
    }

    @Test
    public void uploadResult_WithDifferentScores_Test() {
        Result perfect = resultService.uploadResult("1", "English", 100.0, "A");
        Result high = resultService.uploadResult("2", "English", 85.5, "B");
        Result low = resultService.uploadResult("3", "English", 50.0, "D");
        Result fail = resultService.uploadResult("4", "English", 25.0, "F");
        
        assertEquals(100.0, perfect.getScore());
        assertEquals(85.5, high.getScore());
        assertEquals(50.0, low.getScore());
        assertEquals(25.0, fail.getScore());
    }

    @Test
    public void getStudentResults_MultipleSubjects_Test() {
        resultService.uploadResult("1", "Mathematics", 85.0, "B");
        resultService.uploadResult("1", "Science", 92.0, "A");
        resultService.uploadResult("1", "English", 78.0, "B");
        resultService.uploadResult("1", "History", 88.0, "B");
        
        List<Result> results = resultService.getStudentResults("1");
        
        assertEquals(4, results.size());
    }

    @Test
    public void result_ContainsAllFields_Test() {
        Result result = resultService.uploadResult("student1", "Chemistry", 87.5, "B");
        
        assertNotNull(result.getId());
        assertNotNull(result.getStudentId());
        assertNotNull(result.getSubject());
        assertNotNull(result.getScore());
        assertNotNull(result.getGrade());
        assertEquals("student1", result.getStudentId());
        assertEquals("Chemistry", result.getSubject());
        assertEquals(87.5, result.getScore());
        assertEquals("B", result.getGrade());
    }

    @Test
    public void multipleStudents_SameSubject_DifferentResults_Test() {
        resultService.uploadResult("s1", "Physics", 95.0, "A");
        resultService.uploadResult("s2", "Physics", 75.0, "C");
        resultService.uploadResult("s3", "Physics", 85.0, "B");
        
        Result s1Result = resultService.getResultByStudentAndSubject("s1", "Physics");
        Result s2Result = resultService.getResultByStudentAndSubject("s2", "Physics");
        
        assertEquals(95.0, s1Result.getScore());
        assertEquals(75.0, s2Result.getScore());
    }

    @Test
    public void uploadResult_DifferentSubjects_Test() {
        Result math = resultService.uploadResult("1", "Mathematics", 88.0, "B");
        Result science = resultService.uploadResult("1", "Science", 92.0, "A");
        Result literature = resultService.uploadResult("1", "Literature", 80.0, "B");
        
        assertEquals("Mathematics", math.getSubject());
        assertEquals("Science", science.getSubject());
        assertEquals("Literature", literature.getSubject());
    }

    @Test
    public void getAllResults_EmptyList_Initially_Test() {
        List<Result> results = resultService.getAllResults();
        
        assertNotNull(results);
        assertEquals(0, results.size());
    }
}
