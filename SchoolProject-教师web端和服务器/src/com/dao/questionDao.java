package com.dao;

import com.entity.courseQuestion;
import com.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/4/25.
 */
public class questionDao {

    // /插入习题
    public void insertQuestion(courseQuestion cq)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DBHelper.getConnection();
            String sql = "insert into course_subject(newcourse_ID,teacher_ID,zhang,jie,question_Type,question_Point,question_Content,A,B,C,D,question_Key) value(?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,cq.getNewcourseID());
            stmt.setString(2,cq.getTeacherID());
            stmt.setInt(3,cq.getZhanID());
            stmt.setInt(4,cq.getJieID());
            stmt.setInt(5,cq.getQuestionType());
            stmt.setString(6,cq.getQuestionPoint());
            stmt.setString(7,cq.getQuestionContent());
            stmt.setString(8,cq.getKeyA());
            stmt.setString(9,cq.getKeyB());
            stmt.setString(10,cq.getKeyC());
            stmt.setString(11,cq.getKeyD());
            stmt.setString(12,cq.getRightKey());
            stmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //根据老师的ID和新课程的ID获取老师对一门的添加的全部课程。

    public ArrayList<courseQuestion> getAllQuestion(String newcourseID, String teacherID)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<courseQuestion> list = new ArrayList<courseQuestion>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select * From course_subject where (teacher_ID = ? and newcourse_ID = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,teacherID);
            stmt.setString(2,newcourseID);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                courseQuestion cq = new courseQuestion();
                cq.setQuestionID(rs.getInt("question_ID"));
                cq.setTeacherID(rs.getString("teacher_ID"));
                cq.setNewcourseID(rs.getString("newcourse_ID"));
                cq.setZhanID(rs.getInt("zhang"));
                cq.setJieID(rs.getInt("jie"));
                cq.setQuestionType(rs.getInt("question_Type"));
                cq.setQuestionPoint(rs.getString("question_Point"));
                cq.setQuestionContent(rs.getString("question_Content"));
                list.add(cq);
            }
            return list;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //根据习题的ID读取习题的全部资料
    public courseQuestion getQuestionById(int id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        courseQuestion cq = new courseQuestion();
        try{
            conn = DBHelper.getConnection();
            String sql = "select * From course_subject where question_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                cq.setQuestionID(rs.getInt("question_ID"));
                cq.setTeacherID(rs.getString("teacher_ID"));
                cq.setNewcourseID(rs.getString("newcourse_ID"));
                cq.setZhanID(rs.getInt("zhang"));
                cq.setJieID(rs.getInt("jie"));
                cq.setQuestionType(rs.getInt("question_Type"));
                cq.setQuestionPoint(rs.getString("question_Point"));
                cq.setQuestionContent(rs.getString("question_Content"));
                cq.setKeyA(rs.getString("A"));
                cq.setKeyB(rs.getString("B"));
                cq.setKeyC(rs.getString("C"));
                cq.setKeyD(rs.getString("D"));
                cq.setRightKey(rs.getString("question_Key"));
                return cq;

            }
            else
                return null;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //更新习题
    public void updateQuestion(courseQuestion cq, int questionID)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DBHelper.getConnection();
            String sql = "UPDATE course_subject SET zhang = ?,jie = ?,question_Type = ?,question_Point = ?," +
                    "question_Content = ?,A = ?,B =?,C = ?,D = ?,question_Key = ? WHERE question_ID ="+questionID;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,cq.getZhanID());
            stmt.setInt(2,cq.getJieID());
            stmt.setInt(3,cq.getQuestionType());
            stmt.setString(4,cq.getQuestionPoint());
            stmt.setString(5,cq.getQuestionContent());
            stmt.setString(6,cq.getKeyA());
            stmt.setString(7,cq.getKeyB());
            stmt.setString(8,cq.getKeyC());
            stmt.setString(9,cq.getKeyD());
            stmt.setString(10,cq.getRightKey());
            stmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //更新状态
    public void updateType(int questionID, int Type)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DBHelper.getConnection();
            String sql = "UPDATE course_subject SET question_Type = ? WHERE question_ID ="+questionID;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,Type);
            stmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
