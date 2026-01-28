package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AttendanceRepository {

    private final List<Attendance> attendances = new ArrayList<>();

    public Attendance save(Attendance attendance) {
        if (attendance.getId() == null) {
            attendance.setId(String.valueOf(attendances.size() + 1));
        }
        attendances.add(attendance);
        return attendance;
    }

    public List<Attendance> findAll() {
        return attendances;
    }

    public Optional<Attendance> findById(String id) {
        return attendances.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public List<Attendance> findByStudentId(String studentId) {
        return attendances.stream().filter(a -> a.getStudentId().equals(studentId)).toList();
    }

    public List<Attendance> findByClassId(String classId) {
        return attendances.stream().filter(a -> a.getClassId().equals(classId)).toList();
    }
}
