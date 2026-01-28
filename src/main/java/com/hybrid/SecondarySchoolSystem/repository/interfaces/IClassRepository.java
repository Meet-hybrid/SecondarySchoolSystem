package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.SchoolClass;

import java.util.List;
import java.util.Optional;

public interface IClassRepository {
    SchoolClass save(SchoolClass schoolClass);
    List<SchoolClass> findAll();
    Optional<SchoolClass> findById(String id);
    Optional<SchoolClass> findByClassName(String className);
}
