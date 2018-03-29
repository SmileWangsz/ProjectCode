package com.util;

import com.entity.Course;

import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
public class ResultGroup {

    private int result;
    private int sumA;
    private int sumB;
    private int sumC;
    private int sumD;
    private int sumAll;
    private double accuracy;

    public ResultGroup() {
        super();
    }

    public ResultGroup(int result, int sumA, int sumB, int sumC, int sumD, int sumAll, double accuracy) {
        this.result = result;
        this.sumA = sumA;
        this.sumB = sumB;
        this.sumC = sumC;
        this.sumD = sumD;
        this.sumAll = sumAll;
        this.accuracy = accuracy;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getSumA() {
        return sumA;
    }

    public void setSumA(int sumA) {
        this.sumA = sumA;
    }

    public int getSumB() {
        return sumB;
    }

    public void setSumB(int sumB) {
        this.sumB = sumB;
    }

    public int getSumC() {
        return sumC;
    }

    public void setSumC(int sumC) {
        this.sumC = sumC;
    }

    public int getSumD() {
        return sumD;
    }

    public void setSumD(int sumD) {
        this.sumD = sumD;
    }

    public int getSumAll() {
        return sumAll;
    }

    public void setSumAll(int sumAll) {
        this.sumAll = sumAll;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
