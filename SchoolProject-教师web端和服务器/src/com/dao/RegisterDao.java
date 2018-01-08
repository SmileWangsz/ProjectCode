package com.dao;

import com.entity.TeacherReg;
import com.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Wangsz on 2017/4/22.
 */
public class RegisterDao {

    //注册用户信息
    public void regInfo(TeacherReg tr)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "insert into teachar_reg value(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,tr.getUsername());
            stmt.setString(2,tr.getPassword());
            stmt.setString(3,tr.getTelenumber());
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
}
