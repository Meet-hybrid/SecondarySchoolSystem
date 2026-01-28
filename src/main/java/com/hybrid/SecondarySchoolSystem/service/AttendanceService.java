package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Attendance;
import com.hybrid.SecondarySchoolSystem.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance recordAttendance(String studentId, String classId, String date, String status) {
        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setClassId(classId);
        attendance.setDate(date);
        attendance.setStatus(status);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getStudentAttendance(String studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getClassAttendance(String classId) {
        return attendanceRepository.findByClassId(classId);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
