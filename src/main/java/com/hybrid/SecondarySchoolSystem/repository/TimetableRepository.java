package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Timetable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TimetableRepository {

    private final List<Timetable> timetables = new ArrayList<>();

    public Timetable save(Timetable timetable) {
        if (timetable.getId() == null) {
            timetable.setId(String.valueOf(timetables.size() + 1));
        }
        timetables.add(timetable);
        return timetable;
    }

    public List<Timetable> findAll() {
        return timetables;
    }

    public Optional<Timetable> findById(String id) {
        return timetables.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public List<Timetable> findByClassId(String classId) {
        return timetables.stream().filter(t -> t.getClassId().equals(classId)).toList();
    }

    public List<Timetable> findByTeacherId(String teacherId) {
        return timetables.stream().filter(t -> t.getTeacherId().equals(teacherId)).toList();
    }
}
