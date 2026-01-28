package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(String.valueOf(students.size() + 1));
        }
        students.add(student);
        return student;
    }

    public List<Student> findAll() {
        return students;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> findByName(String name) {
        return students.stream().filter(s -> s.getName().equals(name)).findFirst();
    }
}
