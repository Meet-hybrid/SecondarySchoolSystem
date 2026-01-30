package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(String.valueOf(idGenerator.getAndIncrement()));
        }
        students.add(student);
        return student;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }


    public Optional<Student> findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst();
    }

    public Optional<Student> findByRegistrationNumber(String registrationNumber) {
        return students.stream()
                .filter(s -> s.getRegistrationNumber().equals(registrationNumber))
                .findFirst();
    }

    public Optional<Student> findById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public void deleteById(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }
}
