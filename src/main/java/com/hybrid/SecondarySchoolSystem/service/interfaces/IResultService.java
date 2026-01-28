package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Result;

import java.util.List;

public interface IResultService {
    Result uploadResult(String studentId, String subject, Double score, String grade);
    List<Result> getStudentResults(String studentId);
    List<Result> getAllResults();
    Result getResultByStudentAndSubject(String studentId, String subject);
}
