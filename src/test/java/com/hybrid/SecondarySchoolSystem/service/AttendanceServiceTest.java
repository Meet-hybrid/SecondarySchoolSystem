package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Attendance;
import com.hybrid.SecondarySchoolSystem.repository.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceServiceTest {
    AttendanceService attendanceService;
    AttendanceRepository attendanceRepository;

    @BeforeEach
    void setUp() {
        attendanceRepository = new AttendanceRepository();
        attendanceService = new AttendanceService(attendanceRepository);
    }

    @Test
    public void recordAttendance_Success_Test() {
        Attendance attendance = attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");

        assertNotNull(attendance);
        assertEquals("1", attendance.getStudentId());
        assertEquals("SSS3A", attendance.getClassId());
        assertEquals("PRESENT", attendance.getStatus());
    }

    @Test
    public void getStudentAttendance_Success_Test() {
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-29", "ABSENT");

        List<Attendance> attendances = attendanceService.getStudentAttendance("1");
        assertEquals(2, attendances.size());
    }

    @Test
    public void getClassAttendance_Success_Test() {
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");
        attendanceService.recordAttendance("2", "SSS3A", "2025-01-28", "ABSENT");

        List<Attendance> attendances = attendanceService.getClassAttendance("SSS3A");
        assertEquals(2, attendances.size());
    }

    @Test
    public void getAllAttendance_Success_Test() {
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");
        attendanceService.recordAttendance("1", "SSS3B", "2025-01-28", "ABSENT");

        List<Attendance> attendances = attendanceService.getAllAttendance();
        assertEquals(2, attendances.size());
    }

    @Test
    public void recordAttendance_HasValidId_Test() {
        Attendance attendance = attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");
        
        assertNotNull(attendance.getId());
        assertFalse(attendance.getId().isEmpty());
    }

    @Test
    public void getStudentAttendance_EmptyList_WhenNoRecords_Test() {
        List<Attendance> attendances = attendanceService.getStudentAttendance("nonexistent");
        
        assertNotNull(attendances);
        assertEquals(0, attendances.size());
    }

    @Test
    public void recordAttendance_WithDifferentStatuses_Test() {
        Attendance present = attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");
        Attendance absent = attendanceService.recordAttendance("2", "SSS3A", "2025-01-28", "ABSENT");
        Attendance late = attendanceService.recordAttendance("3", "SSS3A", "2025-01-28", "LATE");
        
        assertEquals("PRESENT", present.getStatus());
        assertEquals("ABSENT", absent.getStatus());
        assertEquals("LATE", late.getStatus());
    }

    @Test
    public void getClassAttendance_MultipleStudents_Test() {
        attendanceService.recordAttendance("st1", "ClassA", "2025-01-28", "PRESENT");
        attendanceService.recordAttendance("st2", "ClassA", "2025-01-28", "PRESENT");
        attendanceService.recordAttendance("st3", "ClassA", "2025-01-28", "ABSENT");
        attendanceService.recordAttendance("st1", "ClassB", "2025-01-28", "PRESENT");
        
        List<Attendance> classA = attendanceService.getClassAttendance("ClassA");
        List<Attendance> classB = attendanceService.getClassAttendance("ClassB");
        
        assertEquals(3, classA.size());
        assertEquals(1, classB.size());
    }

    @Test
    public void attendance_ContainsAllFields_Test() {
        Attendance attendance = attendanceService.recordAttendance("student123", "SSS1B", "2025-01-28", "PRESENT");
        
        assertNotNull(attendance.getId());
        assertNotNull(attendance.getStudentId());
        assertNotNull(attendance.getClassId());
        assertNotNull(attendance.getDate());
        assertNotNull(attendance.getStatus());
        assertEquals("student123", attendance.getStudentId());
        assertEquals("SSS1B", attendance.getClassId());
    }

    @Test
    public void getStudentAttendance_MultipleEntries_SameDay_DifferentDays_Test() {
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-28", "PRESENT");
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-29", "ABSENT");
        attendanceService.recordAttendance("1", "SSS3A", "2025-01-30", "PRESENT");
        
        List<Attendance> records = attendanceService.getStudentAttendance("1");
        
        assertEquals(3, records.size());
    }

    @Test
    public void recordAttendance_DifferentClassesAndStudents_Test() {
        Attendance a1 = attendanceService.recordAttendance("s1", "ClassA", "2025-01-28", "PRESENT");
        Attendance a2 = attendanceService.recordAttendance("s2", "ClassB", "2025-01-28", "ABSENT");
        Attendance a3 = attendanceService.recordAttendance("s3", "ClassC", "2025-01-28", "PRESENT");
        
        assertNotEquals(a1.getId(), a2.getId());
        assertNotEquals(a2.getId(), a3.getId());
        assertEquals(3, attendanceService.getAllAttendance().size());
    }
}
