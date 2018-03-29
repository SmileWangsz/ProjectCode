package com.util;

import com.entity.SchoolClass;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class SchoolClassGroup {

    private int result;
    private ArrayList<SchoolClass> list;

    public SchoolClassGroup() {
        super();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<SchoolClass> getList() {
        return list;
    }

    public void setList(ArrayList<SchoolClass> list) {
        this.list = list;
    }
}
