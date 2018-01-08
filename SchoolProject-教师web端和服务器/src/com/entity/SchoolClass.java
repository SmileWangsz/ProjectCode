package com.entity;

/**
 * Created by Wangsz on 2017/5/8.
 */
public class SchoolClass {

    private int majorId;
    private int classId;
    private String className;

    public SchoolClass() {
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
