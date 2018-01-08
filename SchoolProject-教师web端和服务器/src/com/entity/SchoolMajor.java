package com.entity;

/**
 * Created by Wangsz on 2017/5/8.
 */
public class SchoolMajor {

    private int majorId;
    private int levelId;
    private String majorName;

    public SchoolMajor() {
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
