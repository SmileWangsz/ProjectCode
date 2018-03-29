package com.util;

import com.entity.Course;
import com.entity.TeacherName;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class TeacherNameGroup {

    private int result;
    private ArrayList<TeacherName> list;

    public TeacherNameGroup() {
        super();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<TeacherName> getList() {
        return list;
    }

    public void setList(ArrayList<TeacherName> list) {
        this.list = list;
    }
}
