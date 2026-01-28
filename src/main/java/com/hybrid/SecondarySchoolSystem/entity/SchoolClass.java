package com.hybrid.SecondarySchoolSystem.entity;

public class SchoolClass {
    private String id;
    private String className;
    private String level;
    private String classTeacherId;

    public SchoolClass() {
    }

    public SchoolClass(String className, String level, String classTeacherId) {
        this.className = className;
        this.level = level;
        this.classTeacherId = classTeacherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassTeacherId() {
        return classTeacherId;
    }

    public void setClassTeacherId(String classTeacherId) {
        this.classTeacherId = classTeacherId;
    }
}
