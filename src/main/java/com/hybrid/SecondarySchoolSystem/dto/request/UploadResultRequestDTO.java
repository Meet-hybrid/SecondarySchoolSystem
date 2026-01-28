package com.hybrid.SecondarySchoolSystem.dto.request;

public class UploadResultRequestDTO {
    private String studentId;
    private String subject;
    private Double score;
    private String grade;

    public UploadResultRequestDTO() {
    }

    public UploadResultRequestDTO(String studentId, String subject, Double score, String grade) {
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
