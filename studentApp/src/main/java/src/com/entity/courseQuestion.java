package src.com.entity;

/**
 * Created by Wangsz on 2017/4/25.
 */
public class courseQuestion {
    //课程习题实体类
    private int questionID;
    private String newcourseID;
    private String teacherID;
    private int zhanID;
    private int jieID;
    private int questionType;
    private String questionPoint;
    private String questionContent;
    private String keyA;
    private String keyB;
    private String keyC;
    private String keyD;
    private String rightKey;

    public courseQuestion() {
    }

    public courseQuestion(String newcourseID, String teacherID,int zhanID, int jieID, int questionType,
                          String questionPoint, String questionContent, String keyA,
                          String keyB, String keyC, String keyD, String rightKey) {
        this.newcourseID = newcourseID;
        this.teacherID = teacherID;
        this.zhanID = zhanID;
        this.jieID = jieID;
        this.questionType = questionType;
        this.questionPoint = questionPoint;
        this.questionContent = questionContent;
        this.keyA = keyA;
        this.keyB = keyB;
        this.keyC = keyC;
        this.keyD = keyD;
        this.rightKey = rightKey;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getNewcourseID() {
        return newcourseID;
    }

    public void setNewcourseID(String newcourseID) {
        this.newcourseID = newcourseID;
    }

    public int getZhanID() {
        return zhanID;
    }

    public void setZhanID(int zhanID) {
        this.zhanID = zhanID;
    }

    public int getJieID() {
        return jieID;
    }

    public void setJieID(int jieID) {
        this.jieID = jieID;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestionPoint() {
        return questionPoint;
    }

    public void setQuestionPoint(String questionPoint) {
        this.questionPoint = questionPoint;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getKeyA() {
        return keyA;
    }

    public void setKeyA(String keyA) {
        this.keyA = keyA;
    }

    public String getKeyB() {
        return keyB;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    public String getKeyC() {
        return keyC;
    }

    public void setKeyC(String keyC) {
        this.keyC = keyC;
    }

    public String getKeyD() {
        return keyD;
    }

    public void setKeyD(String keyD) {
        this.keyD = keyD;
    }

    public String getRightKey() {
        return rightKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }
}
