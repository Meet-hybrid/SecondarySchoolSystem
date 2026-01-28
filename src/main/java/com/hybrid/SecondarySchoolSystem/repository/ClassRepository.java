package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.SchoolClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClassRepository {

    private final List<SchoolClass> classes = new ArrayList<>();

    public SchoolClass save(SchoolClass schoolClass) {
        if (schoolClass.getId() == null) {
            schoolClass.setId(String.valueOf(classes.size() + 1));
        }
        classes.add(schoolClass);
        return schoolClass;
    }

    public List<SchoolClass> findAll() {
        return classes;
    }

    public Optional<SchoolClass> findById(String id) {
        return classes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<SchoolClass> findByClassName(String className) {
        return classes.stream().filter(c -> c.getClassName().equals(className)).findFirst();
    }
}
