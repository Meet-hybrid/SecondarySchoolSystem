package com.hybrid.SecondarySchoolSystem.entity;

import java.time.LocalDateTime;

public class Assignment {
    private String id;
    private String title;
    private String description;
    private String teacherId;
    private String classId;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private String filePath;

    public Assignment() {
    }

    public Assignment(String title, String description, String teacherId, String classId, LocalDateTime dueDate, String filePath) {
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.classId = classId;
        this.dueDate = dueDate;
        this.filePath = filePath;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
