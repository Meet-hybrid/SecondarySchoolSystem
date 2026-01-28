package com.hybrid.SecondarySchoolSystem.dto.request;

public class CreateStudentRequestDTO {
    private String name;
    private String classLevel;
    private String department;

    public CreateStudentRequestDTO() {
    }

    public CreateStudentRequestDTO(String name, String classLevel, String department) {
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
