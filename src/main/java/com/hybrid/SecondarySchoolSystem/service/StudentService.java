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

   public Student createStudent(String firstName, String lastName, String registrationNumber, String level, String classId, String parentEmail){

        // check if a student with same name exists
       if (studentRepository.findByRegistrationNumber(registrationNumber).isPresent())
           throw new  DuplicateStudentException("Student with registration number" + registrationNumber + "already exists");

       return studentRepository.save(student);
   }

   public List<Student> getAllStudents() {
       return studentRepository.findAll();
   }
}
