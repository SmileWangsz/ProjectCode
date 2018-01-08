package com.util;

import com.entity.courseQuestion;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/11.
 */
public class ShowCourseAllQuestionGroup {

    private int result;
    private ArrayList<courseQuestion> list;

    public ShowCourseAllQuestionGroup() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<courseQuestion> getList() {
        return list;
    }

    public void setList(ArrayList<courseQuestion> list) {
        this.list = list;
    }
}
