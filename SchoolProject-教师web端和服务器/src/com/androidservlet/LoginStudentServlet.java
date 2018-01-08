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
 * Created by Wangsz on 2017/5/7.
 */
@WebServlet(name = "LoginStudentServlet",urlPatterns = "/com/androidservlet/LoginStudentServlet")
public class LoginStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String userName, passWord;
        boolean flag = false;

        userName = request.getParameter("userName");
        passWord = request.getParameter("passWord");

        studentDAO sDao = new studentDAO();
        flag = sDao.readInfo(userName,passWord);

        PrintWriter out = response.getWriter();

        if(flag)
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
