package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Result;
import com.hybrid.SecondarySchoolSystem.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Result uploadResult(String studentId, String subject, Double score, String grade) {
        Result result = new Result();
        result.setStudentId(studentId);
        result.setSubject(subject);
        result.setScore(score);
        result.setGrade(grade);
        return resultRepository.save(result);
    }

    public List<Result> getStudentResults(String studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result getResultByStudentAndSubject(String studentId, String subject) {
        return resultRepository.findByStudentIdAndSubject(studentId, subject).orElse(null);
    }
}
