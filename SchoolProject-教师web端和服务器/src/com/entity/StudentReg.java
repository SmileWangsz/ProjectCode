package com.entity;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class StudentReg {

    //注意类名要首字母要大些，这是常识。
    private String username;
    private String password;
    private String telenumber;

    public StudentReg() {
        super();
    }

    public StudentReg(String username, String password, String telenumber) {
        this.username = username;
        this.password = password;
        this.telenumber = telenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelenumber() {
        return telenumber;
    }

    public void setTelenumber(String telenumber) {
        this.telenumber = telenumber;
    }
}
