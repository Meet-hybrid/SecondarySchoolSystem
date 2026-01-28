package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Student;
import com.hybrid.SecondarySchoolSystem.exceptions.DuplicateStudentException;
import com.hybrid.SecondarySchoolSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

   public Student createStudent(String name, String classLevel, String department){
        // For simplicity, check if a student with same name exists
        if (studentRepository.findByName(name).isPresent()) {
            throw new DuplicateStudentException("Student already exists");
        }
       Student student = new Student(null, name, classLevel, department);
       return studentRepository.save(student);
   }

   public List<Student> getAllStudents() {
       return studentRepository.findAll();
   }
}
