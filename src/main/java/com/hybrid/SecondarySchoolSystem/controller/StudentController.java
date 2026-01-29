package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.CreateStudentRequestDTO;
import com.hybrid.SecondarySchoolSystem.dto.response.StudentResponse;
import com.hybrid.SecondarySchoolSystem.entity.Student;
import com.hybrid.SecondarySchoolSystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequestDTO request) {
        Student student = studentService.createStudent(
                request.getFirstName(),
                request.getLastName(),
                request.getRegistrationNumber(),
                request.getLevel(),
                request.getClassId(),
                request.getParentEmail()
        );
        StudentResponse studentResponse = new StudentResponse(
                student.getName(),
                student.getClassLevel(),
                student.setDepartment()
        );
        return ResponseEntity.ok(studentResponse);
    }



    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentResponse> responses = students.stream()
                .map(student -> new StudentResponse(
                        student.getName(),
                        student.getClassLevel(),
                        student.getDepartment()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
