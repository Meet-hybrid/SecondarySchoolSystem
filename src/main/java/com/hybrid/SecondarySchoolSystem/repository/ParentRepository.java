package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Parent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ParentRepository {

    private final List<Parent> parents = new ArrayList<>();

    public Parent save(Parent parent) {
        if (parent.getId() == null) {
            parent.setId(String.valueOf(parents.size() + 1));
        }
        parents.add(parent);
        return parent;
    }

    public List<Parent> findAll() {
        return parents;
    }

    public Optional<Parent> findById(String id) {
        return parents.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Parent> findByStudentId(String studentId) {
        return parents.stream().filter(p -> p.getStudentId().equals(studentId)).toList();
    }

    public Optional<Parent> findByEmail(String email) {
        return parents.stream().filter(p -> p.getEmail().equals(email)).findFirst();
    }
}
