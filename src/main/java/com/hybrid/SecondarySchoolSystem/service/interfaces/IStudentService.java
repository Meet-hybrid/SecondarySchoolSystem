package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Student;

import java.util.List;

public interface IStudentService {
    Student createStudent(String name, String classLevel, String department);
    List<Student> getAllStudents();
}
