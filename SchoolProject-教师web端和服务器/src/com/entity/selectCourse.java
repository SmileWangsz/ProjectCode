package com.entity;

import java.util.Date;

/**
 * Created by Wangsz on 2017/4/24.
 */
public class selectCourse {

    //选课实体类
    private String newcourseID;
    private String teacherID;
    private String courseID;
    private Date courseTime;
    private int courseType;

    public selectCourse() {
        super();
    }

    public selectCourse(String newcourseID, String teacherID, String courseID, Date courseTime, int courseType) {
        this.newcourseID = newcourseID;
        this.teacherID = teacherID;
        this.courseID = courseID;
        this.courseTime = courseTime;
        this.courseType = courseType;
    }

    public String getNewcourseID() {
        return newcourseID;
    }

    public void setNewcourseID(String newcourseID) {
        this.newcourseID = newcourseID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Date getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Date courseTime) {
        this.courseTime = courseTime;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }
}
