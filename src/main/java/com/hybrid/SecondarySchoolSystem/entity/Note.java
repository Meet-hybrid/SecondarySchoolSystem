package com.hybrid.SecondarySchoolSystem.entity;

import java.time.LocalDateTime;

public class Note {
    private String id;
    private String title;
    private String content;
    private String teacherId;
    private String classId;
    private LocalDateTime createdAt;
    private String filePath;

    public Note() {
    }

    public Note(String title, String content, String teacherId, String classId, String filePath) {
        this.title = title;
        this.content = content;
        this.teacherId = teacherId;
        this.classId = classId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
