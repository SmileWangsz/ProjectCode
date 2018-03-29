package com.androidservlet;

import com.clientdao.studentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wangsz on 2017/5/11.
 */
@WebServlet(name = "UpdateCourseTypeServlet",urlPatterns = "/com/androidservlet/UpdateCourseTypeServlet")
public class UpdateCourseTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String studentCourseId;
        boolean flag = false;
        studentCourseId = request.getParameter("studentCourseId");

        studentDAO sDao = new studentDAO();
        flag = sDao.updateStudentCourseType(studentCourseId);

        if(flag)
        {
            PrintWriter out = response.getWriter();
            out.write("success");
        }
        else
        {
            PrintWriter out = response.getWriter();
            out.write("failure");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
