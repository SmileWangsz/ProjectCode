package com.entity;

/**
 * Created by Wangsz on 2017/5/9.
 */
public class Course {

    private int courseId;
    private String courseName;

    public Course() {
        super();
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
