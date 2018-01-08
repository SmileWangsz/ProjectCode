package com.entity;

/**
 * Created by Wangsz on 2017/5/10.
 */
public class StudentCourse {

    private String newCourseId;
    private String studentId;
    private int courseId;
    private String teacherId;
    private int courseType;

    public StudentCourse() {
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public StudentCourse(String newCourseId, String studentId, int courseId, String teacherId, int courseType) {
        this.newCourseId = newCourseId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.courseType = courseType;
    }

    public String getNewCourseId() {
        return newCourseId;
    }

    public void setNewCourseId(String newCourseId) {
        this.newCourseId = newCourseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
