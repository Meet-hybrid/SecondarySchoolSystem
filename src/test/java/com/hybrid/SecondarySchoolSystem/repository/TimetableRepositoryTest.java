package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Timetable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableRepositoryTest {

    private TimetableRepository timetableRepository;

    @BeforeEach
    void setUp() {
        timetableRepository = new TimetableRepository();
    }

    @Test
    public void testSaveTimetable() {
        Timetable timetable = new Timetable("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        Timetable savedTimetable = timetableRepository.save(timetable);

        assertNotNull(savedTimetable);
        assertNotNull(savedTimetable.getId());
        assertEquals("C001", savedTimetable.getClassId());
        assertEquals("Monday", savedTimetable.getDay());
    }

    @Test
    public void testFindAll() {
        Timetable timetable1 = new Timetable("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        Timetable timetable2 = new Timetable("C002", "Tuesday", "09:00", "10:00", "English", "T002");

        timetableRepository.save(timetable1);
        timetableRepository.save(timetable2);

        List<Timetable> allTimetables = timetableRepository.findAll();

        assertNotNull(allTimetables);
        assertEquals(2, allTimetables.size());
    }

    @Test
    public void testFindById() {
        Timetable timetable = new Timetable("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        Timetable savedTimetable = timetableRepository.save(timetable);

        Optional<Timetable> foundTimetable = timetableRepository.findById(savedTimetable.getId());

        assertTrue(foundTimetable.isPresent());
        assertEquals(savedTimetable.getId(), foundTimetable.get().getId());
    }

    @Test
    public void testFindByClassId() {
        Timetable t1 = new Timetable("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        Timetable t2 = new Timetable("C001", "Tuesday", "09:00", "10:00", "English", "T002");
        Timetable t3 = new Timetable("C002", "Wednesday", "10:00", "11:00", "Science", "T003");

        timetableRepository.save(t1);
        timetableRepository.save(t2);
        timetableRepository.save(t3);

        List<Timetable> class1Timetables = timetableRepository.findByClassId("C001");

        assertNotNull(class1Timetables);
        assertEquals(2, class1Timetables.size());
    }

    @Test
    public void testFindByTeacherId() {
        Timetable t1 = new Timetable("C001", "Monday", "08:00", "09:00", "Mathematics", "T001");
        Timetable t2 = new Timetable("C002", "Tuesday", "09:00", "10:00", "Mathematics", "T001");
        Timetable t3 = new Timetable("C003", "Wednesday", "10:00", "11:00", "English", "T002");

        timetableRepository.save(t1);
        timetableRepository.save(t2);
        timetableRepository.save(t3);

        List<Timetable> teacher1Timetables = timetableRepository.findByTeacherId("T001");

        assertNotNull(teacher1Timetables);
        assertEquals(2, teacher1Timetables.size());
    }

    @Test
    public void testEmptyRepository() {
        List<Timetable> allTimetables = timetableRepository.findAll();

        assertNotNull(allTimetables);
        assertEquals(0, allTimetables.size());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<Timetable> foundTimetable = timetableRepository.findById("NONEXISTENT");

        assertFalse(foundTimetable.isPresent());
    }

    @Test
    public void testDifferentDaysAndTimes() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        
        for (int i = 0; i < days.length; i++) {
            Timetable timetable = new Timetable("C001", days[i], "08:00", "09:00", "Subject" + i, "T001");
            timetableRepository.save(timetable);
        }

        List<Timetable> allTimetables = timetableRepository.findAll();

        assertEquals(5, allTimetables.size());
    }
}
