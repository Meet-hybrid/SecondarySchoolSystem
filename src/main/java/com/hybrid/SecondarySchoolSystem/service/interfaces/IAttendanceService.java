package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Attendance;

import java.util.List;

public interface IAttendanceService {
    Attendance recordAttendance(String studentId, String classId, String date, String status);
    List<Attendance> getStudentAttendance(String studentId);
    List<Attendance> getClassAttendance(String classId);
    List<Attendance> getAllAttendance();
}
