package com.hybrid.SecondarySchoolSystem.entity;

public class Student {
    private String id;
    private String name;
    private String classLevel;
    private String department;

    public Student() {
    }

    public Student(String id, String name, String classLevel, String department) {
        this.id = id;
        this.name = name;
        this.classLevel = classLevel;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
