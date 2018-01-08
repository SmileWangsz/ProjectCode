package com.entity;

/**
 * Created by Wangsz on 2017/5/13.
 */
public class StudentRecord {

    private String studentId;
    private int releaseId;
    private String answer;

    public StudentRecord() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
