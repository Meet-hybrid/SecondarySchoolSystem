package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Timetable;

import java.util.List;
import java.util.Optional;

public interface ITimetableRepository {
    Timetable save(Timetable timetable);
    List<Timetable> findAll();
    Optional<Timetable> findById(String id);
    List<Timetable> findByClassId(String classId);
    List<Timetable> findByTeacherId(String teacherId);
}
