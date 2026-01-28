package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Result;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultRepository {

    private final List<Result> results = new ArrayList<>();

    public Result save(Result result) {
        if (result.getId() == null) {
            result.setId(String.valueOf(results.size() + 1));
        }
        results.add(result);
        return result;
    }

    public List<Result> findAll() {
        return results;
    }

    public Optional<Result> findById(String id) {
        return results.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Result> findByStudentId(String studentId) {
        return results.stream().filter(r -> r.getStudentId().equals(studentId)).toList();
    }

    public Optional<Result> findByStudentIdAndSubject(String studentId, String subject) {
        return results.stream()
                .filter(r -> r.getStudentId().equals(studentId) && r.getSubject().equals(subject))
                .findFirst();
    }
}
