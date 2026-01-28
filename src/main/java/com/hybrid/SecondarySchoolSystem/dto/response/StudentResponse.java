package com.hybrid.SecondarySchoolSystem.dto.response;

public class StudentResponse {
    private String name;
    private String classLevel;
    private String department;

    public StudentResponse() {
    }

    public StudentResponse(String name, String classLevel, String department) {
        this.name = name;
        this.classLevel = classLevel;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
