package com.util;

import com.entity.ShowCourse;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/10.
 */
public class ShowStudentCourseGroup {

    private int result;
    private ArrayList<ShowCourse> list;

    public ShowStudentCourseGroup() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<ShowCourse> getList() {
        return list;
    }

    public void setList(ArrayList<ShowCourse> list) {
        this.list = list;
    }
}
