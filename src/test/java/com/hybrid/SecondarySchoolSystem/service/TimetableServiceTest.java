package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Timetable;
import com.hybrid.SecondarySchoolSystem.repository.TimetableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimetableServiceTest {
    TimetableService timetableService;
    TimetableRepository timetableRepository;

    @BeforeEach
    void setUp() {
        timetableRepository = new TimetableRepository();
        timetableService = new TimetableService(timetableRepository);
    }

    @Test
    public void createTimetableEntry_Success_Test() {
        Timetable timetable = timetableService.createTimetableEntry(
                "SSS3A",
                "Monday",
                "09:00",
                "10:00",
                "Mathematics",
                "teacher1"
        );

        assertNotNull(timetable);
        assertEquals("SSS3A", timetable.getClassId());
        assertEquals("Monday", timetable.getDay());
        assertEquals("Mathematics", timetable.getSubject());
    }

    @Test
    public void getClassTimetable_Success_Test() {
        timetableService.createTimetableEntry("SSS3A", "Monday", "09:00", "10:00", "Mathematics", "teacher1");
        timetableService.createTimetableEntry("SSS3A", "Tuesday", "10:00", "11:00", "English", "teacher2");

        List<Timetable> timetables = timetableService.getClassTimetable("SSS3A");
        assertEquals(2, timetables.size());
    }

    @Test
    public void getTeacherTimetable_Success_Test() {
        timetableService.createTimetableEntry("SSS3A", "Monday", "09:00", "10:00", "Mathematics", "teacher1");
        timetableService.createTimetableEntry("SSS3B", "Monday", "10:00", "11:00", "Mathematics", "teacher1");

        List<Timetable> timetables = timetableService.getTeacherTimetable("teacher1");
        assertEquals(2, timetables.size());
    }

    @Test
    public void getAllTimetables_Success_Test() {
        timetableService.createTimetableEntry("SSS3A", "Monday", "09:00", "10:00", "Mathematics", "teacher1");
        timetableService.createTimetableEntry("SSS3B", "Tuesday", "10:00", "11:00", "English", "teacher2");

        List<Timetable> timetables = timetableService.getAllTimetables();
        assertEquals(2, timetables.size());
    }
}
