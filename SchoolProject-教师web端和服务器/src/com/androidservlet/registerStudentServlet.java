package com.androidservlet;

import com.clientdao.studentDAO;
import com.entity.StudentReg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wangsz on 2017/5/7.
 */
@WebServlet(name = "registerStudentServlet",urlPatterns = "/com/androidservlet/registerStudentServlet")
public class registerStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");//如果没有这一句，他没法打印网页代码。

        String id, phone, password;

        boolean falg = false;

        id = request.getParameter("id");
        phone = request.getParameter("phone");
        password = request.getParameter("password");

        StudentReg sr = new StudentReg(id,password,phone);
        studentDAO sDao = new studentDAO();
        falg = sDao.regInfo(sr);
        PrintWriter out = response.getWriter();
        if(falg)
        {
            out.write("success");
        }
        else
            out.write("failure");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request,response);

    }
}
