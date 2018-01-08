package com.util;

import com.entity.SchoolLevel;
import com.entity.SchoolTime;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class SchoolTimeGroup {

    private int result;
    private ArrayList<SchoolTime> list;

    public SchoolTimeGroup() {
        super();
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<SchoolTime> getList() {
        return list;
    }

    public void setList(ArrayList<SchoolTime> list) {
        this.list = list;
    }
}
