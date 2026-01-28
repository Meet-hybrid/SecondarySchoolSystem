package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Parent;

import java.util.List;
import java.util.Optional;

public interface IParentRepository {
    Parent save(Parent parent);
    List<Parent> findAll();
    Optional<Parent> findById(String id);
    List<Parent> findByStudentId(String studentId);
    Optional<Parent> findByEmail(String email);
}
