package com.dao;

import com.entity.QuestionRelease;
import com.entity.StudentRecord;
import com.entity.selectCourse;
import com.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/4/24.
 */
public class selectCourseDao {

    //建立新的课程表供学生选取
    public void insertCourse(selectCourse sc)
    {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "insert into select_course value(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,sc.getNewcourseID());
            stmt.setString(2,sc.getTeacherID());
            stmt.setString(3,sc.getCourseID());
            stmt.setDate(4,new java.sql.Date(sc.getCourseTime().getTime()));
            stmt.setInt(5,sc.getCourseType());
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
    //读取一个教师所教的所有课程。
    public ArrayList<selectCourse> getAllCourse(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<selectCourse> list = new ArrayList<selectCourse>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select * From select_course where teacher_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                selectCourse sc = new selectCourse();
                sc.setNewcourseID(rs.getString("newcourse_ID"));
                sc.setTeacherID(rs.getString("teacher_id"));
                sc.setCourseID(rs.getString("course_Id"));
                sc.setCourseTime(rs.getDate("course_Time"));
                sc.setCourseType(rs.getInt("course_Type"));
                list.add(sc);
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

    //根据课程ID获取课程名称
    public String getCourseById(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<selectCourse> list = new ArrayList<selectCourse>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select course_Name From course where course_Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getString("course_Name");
            }
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

    //根据问题的ID读取发布表中，这个问题的所有发布记录
    public ArrayList<QuestionRelease> getAllReleaseRecord(int questionId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<QuestionRelease> list = new ArrayList<QuestionRelease>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select * From question_release where question_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,questionId);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                QuestionRelease qr = new QuestionRelease();
                qr.setReleaseId(rs.getInt("release_ID"));
                qr.setQuestionId(rs.getInt("question_ID"));
                qr.setStartTime(rs.getTimestamp("start_Time"));
                qr.setPeopleNumber(rs.getInt("people_Num"));
                list.add(qr);
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

    //根据发布的ID读取学生答题记录表中所有学生的答题记录
    public ArrayList<StudentRecord> getAllStudentRecord(int releaseId)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<StudentRecord> list = new ArrayList<StudentRecord>();//课程集合

        try{
            conn = DBHelper.getConnection();
            String sql = "select * From student_record where release_Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,releaseId);
            rs = stmt.executeQuery();
            while(rs.next())
            {
                StudentRecord sr = new StudentRecord();
                sr.setStudentId(rs.getString("student_Id"));
                sr.setReleaseId(rs.getInt("release_Id"));
                sr.setAnswer(rs.getString("answer"));
                list.add(sr);
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
}
