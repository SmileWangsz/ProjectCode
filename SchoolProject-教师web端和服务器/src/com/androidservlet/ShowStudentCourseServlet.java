package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.entity.ShowCourse;
import com.util.ShowStudentCourseGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/10.
 */
@WebServlet(name = "ShowStudentCourseServlet",urlPatterns = "/com/androidservlet/ShowStudentCourseServlet")
public class ShowStudentCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String userId;
        userId = request.getParameter("userId");

        studentDAO sDao = new studentDAO();
        ArrayList<ShowCourse> list = sDao.showStudentCourse(userId);

        ShowStudentCourseGroup group = new ShowStudentCourseGroup();
        group.setResult(200);
        group.setList(list);

        String jsonString = JSON.toJSONString(group);
        PrintWriter out = response.getWriter();
        out.write(jsonString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
