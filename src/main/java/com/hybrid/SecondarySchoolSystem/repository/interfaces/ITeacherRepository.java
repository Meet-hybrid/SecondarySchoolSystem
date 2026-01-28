package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherRepository {
    Teacher save(Teacher teacher);
    List<Teacher> findAll();
    Optional<Teacher> findById(String id);
    Optional<Teacher> findByEmail(String email);
}
