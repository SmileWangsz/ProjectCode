package com.entity;

import java.sql.Timestamp;

/**
 * Created by Wangsz on 2017/5/13.
 */
public class QuestionRelease {

    private int releaseId;
    private int questionId;
    private Timestamp startTime;
    private int peopleNumber;

    public QuestionRelease() {
    }

    public int getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }
}
