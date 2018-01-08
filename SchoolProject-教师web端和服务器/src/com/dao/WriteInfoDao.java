package com.dao;

import com.entity.WriteTeacherInfo;
import com.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wangsz on 2017/4/23.
 */
public class WriteInfoDao {

    //完善个人信息
    public void writeInfo(WriteTeacherInfo wti)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DBHelper.getConnection();
            String sql ="insert into teacher_info value(?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,wti.getUser_Id());
            stmt.setString(2,wti.getUserName());
            stmt.setString(3,wti.getGender());
            stmt.setDate(4, new java.sql.Date( wti.getTime().getTime()));//时间写入数据库的标准写法。
            stmt.setString(5,wti.getTelNumber());
            stmt.setString(6,wti.getEmail());
            stmt.executeUpdate();
        }catch (Exception ex)
        {
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

    //读取个人信息
    public WriteTeacherInfo readInfo(String id)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select * From teacher_info where teacher_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            if(rs.next())
            {
                WriteTeacherInfo wti = new WriteTeacherInfo();
                wti.setUserName(rs.getString("teacher_name"));
                wti.setGender(rs.getString("teacher_sex"));
                wti.setTime(rs.getDate("teacher_time"));//从数据库获取时间的标准格式。
                wti.setTelNumber(rs.getString("teacher_tele"));
                wti.setEmail(rs.getString("teacher_email"));
                return wti;
            }
            else
                return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        finally {
            //释放数据集对象
            if (rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            //释放语句对象
            if (stmt!=null)
            {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
        }

    }

}
