package com.dao;

import com.entity.TeacherLogin;
import com.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wangsz on 2017/4/23.
 */
public class LoginDao {

    //读取用户信息用于登录
    public Integer loginInfo(TeacherLogin tl)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select * FROM teachar_reg WHERE teacher_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,tl.getUserName());
            rs = stmt.executeQuery();
            String pwd = tl.getPassWord();
            if(rs.next())
            {
                if(rs.getString("user_pwd").equals(pwd))
                    return 1;
                else
                    return 0;
            }
            else
                return 0;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
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
