package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.response.TimetableResponseDTO;
import com.hybrid.SecondarySchoolSystem.entity.Timetable;
import com.hybrid.SecondarySchoolSystem.service.TimetableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @PostMapping("/create")
    public ResponseEntity<TimetableResponseDTO> createTimetableEntry(
            @RequestParam String classId,
            @RequestParam String day,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam String subject,
            @RequestParam String teacherId) {
        Timetable timetable = timetableService.createTimetableEntry(classId, day, startTime, endTime, subject, teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(timetable));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<TimetableResponseDTO>> getClassTimetable(@PathVariable String classId) {
        List<Timetable> timetables = timetableService.getClassTimetable(classId);
        return ResponseEntity.ok(timetables.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TimetableResponseDTO>> getTeacherTimetable(@PathVariable String teacherId) {
        List<Timetable> timetables = timetableService.getTeacherTimetable(teacherId);
        return ResponseEntity.ok(timetables.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimetableResponseDTO>> getAllTimetables() {
        List<Timetable> timetables = timetableService.getAllTimetables();
        return ResponseEntity.ok(timetables.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    private TimetableResponseDTO convertToDTO(Timetable timetable) {
        return new TimetableResponseDTO(
                timetable.getId(),
                timetable.getClassId(),
                timetable.getDay(),
                timetable.getStartTime(),
                timetable.getEndTime(),
                timetable.getSubject(),
                timetable.getTeacherId()
        );
    }
}
