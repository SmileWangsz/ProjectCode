package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Wangsz on 2017/3/29.
 */
public class DBHelper {
    private static final String driver = "com.mysql.jdbc.Driver";//数据库驱动
    //链接数据库地址，固定写法。
    private static final String url = "jdbc:mysql://localhost:3306/schoolproject_db?useUnicode=true&characterEncoding=UTF8";
    private static final String username = "root";//数据库用户名
    private static final String password = "123456";//数据库密码
    private static Connection conn = null;
    //静态代码块负责加载驱动。
    static
    {
        try {
            Class.forName(driver);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    //单例模式，返回数据库链接对象。
    public static Connection getConnection() throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

    public static void main(String[] args) {

        try {

            Connection conn = DBHelper.getConnection();
            if (conn != null)
            {
                System.out.printf("数据库连接正常");
            }
            else
            {
                System.out.printf("数据库连接异常");
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
