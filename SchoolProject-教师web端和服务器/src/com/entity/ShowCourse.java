package com.entity;

/**
 * Created by Wangsz on 2017/5/10.
 */
public class ShowCourse {

    private String teacherId;
    private String courseId;
    private String courseName;
    private String teacherName;
    private String newCourseId;

    public String getNewCourseId() {
        return newCourseId;
    }

    public void setNewCourseId(String newCourseId) {
        this.newCourseId = newCourseId;
    }

    public ShowCourse() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
