package com.hybrid.SecondarySchoolSystem.dto.response;

public class AttendanceResponseDTO {
    private String id;
    private String studentId;
    private String classId;
    private String date;
    private String status;

    public AttendanceResponseDTO() {
    }

    public AttendanceResponseDTO(String id, String studentId, String classId, String date, String status) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
