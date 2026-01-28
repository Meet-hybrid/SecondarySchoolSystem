package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Result;

import java.util.List;
import java.util.Optional;

public interface IResultRepository {
    Result save(Result result);
    List<Result> findAll();
    Optional<Result> findById(String id);
    List<Result> findByStudentId(String studentId);
    Optional<Result> findByStudentIdAndSubject(String studentId, String subject);
}
