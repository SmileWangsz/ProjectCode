package com.entity;

import java.util.Date;

/**
 * Created by Wangsz on 2017/4/23.
 */
public class WriteTeacherInfo {
    //教师个人信息类
    private String user_Id;
    private String userName;
    private String gender;
    private Date time;
    private String telNumber;
    private String email;

    public WriteTeacherInfo() {
        super();
    }

    public WriteTeacherInfo(String user_Id, String userName, String gender, Date time, String telNumber, String email) {
        this.user_Id = user_Id;
        this.userName = userName;
        this.gender = gender;
        this.time = time;
        this.telNumber = telNumber;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }
}
