package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    Student save(Student student);
    List<Student> findAll();
    Optional<Student> findById(String id);
    Optional<Student> findByName(String name);
}
