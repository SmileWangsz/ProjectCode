package com.entity;

/**学生信息类。
 * Created by Wangsz on 2017/5/8.
 */
public class StudentInfo {

    private String userID;
    private String userName;
    private String userSex;
    private String userLevel;
    private String userMajor;
    private String userClass;
    private String userTime;
    private String userTele;
    private String userEmail;

    public StudentInfo() {
        super();
    }

    public StudentInfo(String userID, String userName, String userSex,
                       String userLevel, String userMajor, String userClass, String userTime,
                       String userTele, String userEmail) {
        this.userID = userID;
        this.userName = userName;
        this.userSex = userSex;
        this.userLevel = userLevel;
        this.userMajor = userMajor;
        this.userClass = userClass;
        this.userTime = userTime;
        this.userTele = userTele;
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getUserTele() {
        return userTele;
    }

    public void setUserTele(String userTele) {
        this.userTele = userTele;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
