package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Timetable;
import com.hybrid.SecondarySchoolSystem.repository.TimetableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public Timetable createTimetableEntry(String classId, String day, String startTime, String endTime, String subject, String teacherId) {
        Timetable timetable = new Timetable(classId, day, startTime, endTime, subject, teacherId);
        return timetableRepository.save(timetable);
    }

    public List<Timetable> getClassTimetable(String classId) {
        return timetableRepository.findByClassId(classId);
    }

    public List<Timetable> getTeacherTimetable(String teacherId) {
        return timetableRepository.findByTeacherId(teacherId);
    }

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }
}
