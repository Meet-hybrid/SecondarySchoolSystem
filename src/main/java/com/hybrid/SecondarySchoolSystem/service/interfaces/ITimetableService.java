package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Timetable;

import java.util.List;

public interface ITimetableService {
    Timetable createTimetableEntry(String classId, String day, String startTime, String endTime, String subject, String teacherId);
    List<Timetable> getClassTimetable(String classId);
    List<Timetable> getTeacherTimetable(String teacherId);
    List<Timetable> getAllTimetables();
}
