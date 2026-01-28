package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Attendance;

import java.util.List;
import java.util.Optional;

public interface IAttendanceRepository {
    Attendance save(Attendance attendance);
    List<Attendance> findAll();
    Optional<Attendance> findById(String id);
    List<Attendance> findByStudentId(String studentId);
    List<Attendance> findByClassId(String classId);
}
