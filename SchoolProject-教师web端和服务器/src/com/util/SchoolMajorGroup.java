package com.util;


import com.entity.SchoolMajor;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class SchoolMajorGroup {

    private int result;
    private ArrayList<SchoolMajor> list;

    public SchoolMajorGroup() {
        super();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<SchoolMajor> getList() {
        return list;
    }

    public void setList(ArrayList<SchoolMajor> list) {
        this.list = list;
    }
}
