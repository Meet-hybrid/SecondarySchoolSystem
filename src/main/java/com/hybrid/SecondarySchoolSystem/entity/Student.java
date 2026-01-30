package com.hybrid.SecondarySchoolSystem.entity;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String department;
    private String name;
    private String registrationNumber;
    private String classLevel;
    private String classId;
    private String parentEmail;

    public Student() {
    }

    public Student(String id, String name, String classLevel, String registrationNumber, String classId, String parentEmail) {
        this.id = id;
        this.name = name;
        this.classLevel = classLevel;
        this.registrationNumber = registrationNumber;
        this.classId = classId;
        this.parentEmail = parentEmail;

    }

    public Student(String id, String firstName, String lastName, String department, String name, String registrationNumber, String classId, String parentEmail, String classLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + " " + lastName;
        this.classLevel = classLevel;
        this.registrationNumber = registrationNumber;
        this.classId = classId;
        this.parentEmail = parentEmail;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        updateFullName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        updateFullName();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void updateFullName() {
        if (firstName != null && lastName != null) {
            this.name = firstName + " " + lastName;
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
}
