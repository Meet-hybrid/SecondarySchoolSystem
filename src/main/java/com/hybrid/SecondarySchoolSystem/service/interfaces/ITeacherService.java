package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Teacher;

import java.util.List;

public interface ITeacherService {
    Teacher createTeacher(String name, String email, String subject);
    List<Teacher> getAllTeachers();
}
