package com.util;

import com.entity.Course;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class CourseGroup {

    private int result;
    private ArrayList<Course> list;

    public CourseGroup() {
        super();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<Course> getList() {
        return list;
    }

    public void setList(ArrayList<Course> list) {
        this.list = list;
    }
}
