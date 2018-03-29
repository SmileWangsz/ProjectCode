package com.servlet;

import com.dao.selectCourseDao;
import com.entity.selectCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/4/25.
 */
@WebServlet(name = "saveInfoServlet", urlPatterns = "/com/servlet/saveInfoServlet")
public class saveInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseID, newcourseID;
        String id = request.getSession().getAttribute("loginUserId").toString();
        selectCourseDao scDao = new selectCourseDao();
        ArrayList<selectCourse> list = scDao.getAllCourse(id);

        //无效的servlet，如果后续开发没有使用到，可以删除这个servlet。


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
