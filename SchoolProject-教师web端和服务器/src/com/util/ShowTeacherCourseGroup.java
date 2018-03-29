package com.util;

import com.entity.TeacherCourse;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/10.
 */
public class ShowTeacherCourseGroup {

    private int result;
    private ArrayList<TeacherCourse> list;

    public ShowTeacherCourseGroup() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<TeacherCourse> getList() {
        return list;
    }

    public void setList(ArrayList<TeacherCourse> list) {
        this.list = list;
    }
}
