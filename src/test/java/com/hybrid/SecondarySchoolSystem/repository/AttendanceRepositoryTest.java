package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Attendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceRepositoryTest {
    AttendanceRepository attendanceRepository;

    @BeforeEach
    void setUp() {
        attendanceRepository = new AttendanceRepository();
    }

    @Test
    public void save_Success_Test() {
        Attendance attendance = new Attendance();
        attendance.setStudentId("1");
        attendance.setClassId("SSS3A");
        attendance.setDate("2025-01-28");
        attendance.setStatus("PRESENT");

        Attendance saved = attendanceRepository.save(attendance);
        assertNotNull(saved.getId());
    }

    @Test
    public void findByStudentId_Success_Test() {
        Attendance attendance1 = new Attendance();
        attendance1.setStudentId("1");
        attendance1.setClassId("SSS3A");
        attendance1.setStatus("PRESENT");

        attendanceRepository.save(attendance1);
        List<Attendance> result = attendanceRepository.findByStudentId("1");

        assertEquals(1, result.size());
    }

    @Test
    public void findByClassId_Success_Test() {
        Attendance attendance1 = new Attendance();
        attendance1.setStudentId("1");
        attendance1.setClassId("SSS3A");
        attendance1.setStatus("PRESENT");

        attendanceRepository.save(attendance1);
        List<Attendance> result = attendanceRepository.findByClassId("SSS3A");

        assertEquals(1, result.size());
    }
}
