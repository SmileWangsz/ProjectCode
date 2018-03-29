package com.entity;

/**教师注册类
 * Created by Wangsz on 2017/4/22.
 */
public class TeacherReg {

    private String username;
    private String password;
    private String telenumber;


    public TeacherReg(){

        super();
    }

    public TeacherReg(String username, String password, String telenumber) {
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
