package com.entity;

/**用户登录类
 * Created by Wangsz on 2017/4/23.
 */
public class TeacherLogin {
    private String userName;
    private String passWord;

    public TeacherLogin(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public TeacherLogin() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
