package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResultRepositoryTest {
    ResultRepository resultRepository;

    @BeforeEach
    void setUp() {
        resultRepository = new ResultRepository();
    }

    @Test
    public void save_Success_Test() {
        Result result = new Result();
        result.setStudentId("1");
        result.setSubject("Mathematics");
        result.setScore(85.5);
        result.setGrade("A");

        Result saved = resultRepository.save(result);
        assertNotNull(saved.getId());
    }

    @Test
    public void findByStudentId_Success_Test() {
        Result result = new Result();
        result.setStudentId("1");
        result.setSubject("Mathematics");
        result.setScore(85.5);
        result.setGrade("A");

        resultRepository.save(result);
        List<Result> results = resultRepository.findByStudentId("1");

        assertEquals(1, results.size());
    }

    @Test
    public void findByStudentIdAndSubject_Success_Test() {
        Result result = new Result();
        result.setStudentId("1");
        result.setSubject("Mathematics");
        result.setScore(85.5);
        result.setGrade("A");

        resultRepository.save(result);
        Optional<Result> found = resultRepository.findByStudentIdAndSubject("1", "Mathematics");

        assertTrue(found.isPresent());
        assertEquals("Mathematics", found.get().getSubject());
    }
}
