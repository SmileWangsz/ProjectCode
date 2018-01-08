package com.util;

import com.entity.SchoolLevel;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class SchoolLevelGroup {

    private int result;
    private ArrayList<SchoolLevel> list;

    public SchoolLevelGroup() {
        super();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<SchoolLevel> getList() {
        return list;
    }

    public void setList(ArrayList<SchoolLevel> list) {
        this.list = list;
    }
}
