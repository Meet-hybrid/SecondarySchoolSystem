package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.response.TimetableResponseDTO;
import com.hybrid.SecondarySchoolSystem.entity.Timetable;
import com.hybrid.SecondarySchoolSystem.repository.TimetableRepository;
import com.hybrid.SecondarySchoolSystem.service.TimetableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableControllerTest {

    private TimetableRepository timetableRepository;
    private TimetableService timetableService;
    private TimetableController timetableController;

    @BeforeEach
    void setUp() {
        timetableRepository = new TimetableRepository();
        timetableService = new TimetableService(timetableRepository);
        timetableController = new TimetableController(timetableService);
    }

    @Test
    public void testCreateTimetableEntry() {
        Timetable result = timetableService.createTimetableEntry("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");

        assertNotNull(result);
        assertEquals("C001", result.getClassId());
        assertEquals("Monday", result.getDay());
        assertEquals("08:00", result.getStartTime());
        assertEquals("09:00", result.getEndTime());
        assertEquals("Mathematics", result.getSubject());
    }

    @Test
    public void testGetClassTimetable() {
        timetableService.createTimetableEntry("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        timetableService.createTimetableEntry("C001", "Tuesday", "09:00", "10:00", "English", "T002");
        timetableService.createTimetableEntry("C002", "Wednesday", "10:00", "11:00", "Science", "T003");

        List<Timetable> classTimetables = timetableService.getClassTimetable("C001");

        assertNotNull(classTimetables);
        assertEquals(2, classTimetables.size());
    }

    @Test
    public void testGetTeacherTimetable() {
        timetableService.createTimetableEntry("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        timetableService.createTimetableEntry("C002", "Tuesday", "09:00", "10:00", "Mathematics", "T001");
        timetableService.createTimetableEntry("C003", "Wednesday", "10:00", "11:00", "English", "T002");

        List<Timetable> teacherTimetables = timetableService.getTeacherTimetable("T001");

        assertNotNull(teacherTimetables);
        assertEquals(2, teacherTimetables.size());
    }

    @Test
    public void testGetAllTimetables() {
        timetableService.createTimetableEntry("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        timetableService.createTimetableEntry("C002", "Tuesday", "09:00", "10:00", "English", "T002");
        timetableService.createTimetableEntry("C003", "Wednesday", "10:00", "11:00", "Science", "T003");

        List<Timetable> allTimetables = timetableService.getAllTimetables();

        assertNotNull(allTimetables);
        assertEquals(3, allTimetables.size());
    }

    @Test
    public void testMultipleTimetablesForMultipleClasses() {
        // Class 1
        timetableService.createTimetableEntry("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        timetableService.createTimetableEntry("C001", "Tuesday", "09:00", "10:00", "English", "T002");
        timetableService.createTimetableEntry("C001", "Wednesday", "10:00", "11:00", "Science", "T003");

        // Class 2
        timetableService.createTimetableEntry("C002", "Monday", "08:00", "09:00", "Physics", "T001");
        timetableService.createTimetableEntry("C002", "Tuesday", "09:00", "10:00", "Chemistry", "T002");

        List<Timetable> class1 = timetableService.getClassTimetable("C001");
        List<Timetable> class2 = timetableService.getClassTimetable("C002");
        List<Timetable> all = timetableService.getAllTimetables();

        assertEquals(3, class1.size());
        assertEquals(2, class2.size());
        assertEquals(5, all.size());
    }

    @Test
    public void testEmptyTimetable() {
        List<Timetable> emptyResult = timetableService.getClassTimetable("NONEXISTENT");

        assertNotNull(emptyResult);
        assertEquals(0, emptyResult.size());
    }

    @Test
    public void testDifferentSubjects() {
        String[] subjects = {"Mathematics", "English", "Science", "History", "Geography"};
        
        for (int i = 0; i < subjects.length; i++) {
            timetableService.createTimetableEntry("C001", "Day" + i, "08:00", "09:00", subjects[i], "T001");
        }

        List<Timetable> allTimetables = timetableService.getClassTimetable("C001");

        assertEquals(5, allTimetables.size());
    }
}
