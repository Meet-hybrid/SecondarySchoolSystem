package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.RecordAttendanceRequestDTO;
import com.hybrid.SecondarySchoolSystem.dto.response.AttendanceResponseDTO;
import com.hybrid.SecondarySchoolSystem.entity.Attendance;
import com.hybrid.SecondarySchoolSystem.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/record")
    public ResponseEntity<AttendanceResponseDTO> recordAttendance(@RequestBody RecordAttendanceRequestDTO dto) {
        Attendance attendance = attendanceService.recordAttendance(
                dto.getStudentId(),
                dto.getClassId(),
                dto.getDate(),
                dto.getStatus()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(attendance));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getStudentAttendance(@PathVariable String studentId) {
        List<Attendance> attendances = attendanceService.getStudentAttendance(studentId);
        return ResponseEntity.ok(attendances.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getClassAttendance(@PathVariable String classId) {
        List<Attendance> attendances = attendanceService.getClassAttendance(classId);
        return ResponseEntity.ok(attendances.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceResponseDTO>> getAllAttendance() {
        List<Attendance> attendances = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendances.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    private AttendanceResponseDTO convertToDTO(Attendance attendance) {
        return new AttendanceResponseDTO(
                attendance.getId(),
                attendance.getStudentId(),
                attendance.getClassId(),
                attendance.getDate(),
                attendance.getStatus()
        );
    }
}
