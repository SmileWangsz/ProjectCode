package src.com.entity;

/**
 * Created by Wangsz on 2017/5/14.
 */

public class ResultRecord {

    //老师结束发布所得的结果实体类。
    private int sumA;
    private int sumB;
    private int sumC;
    private int sumD;
    private int sumAll;
    private double accuracy;

    public ResultRecord() {
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
