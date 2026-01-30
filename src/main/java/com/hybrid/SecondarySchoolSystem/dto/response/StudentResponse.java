package com.hybrid.SecondarySchoolSystem.dto.response;

public class StudentResponse {
    private String name;
    private String classLevel;
    private String classId;  // Changed from department

    public StudentResponse() {
    }

    public StudentResponse(String name, String classLevel, String classId) {
        this.name = name;
        this.classLevel = classLevel;
        this.classId = classId;  // Changed from department
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getClassId() {  // Changed from getDepartment
        return classId;
    }

    public void setClassId(String classId) {  // Changed from setDepartment
        this.classId = classId;
    }
}