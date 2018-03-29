package com.androidservlet;

import com.clientdao.studentDAO;
import com.entity.StudentInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wangsz on 2017/5/8.
 */
@WebServlet(name = "WriteStudentInfoServlet", urlPatterns = "/com/androidservlet/WriteStudentInfoServlet")
public class WriteStudentInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String userID, userName, userSex, userLevel, userMajor, userClass, userTime, userTele, userEmail;
        boolean flag = false;

        userID = request.getParameter("userID");
        userName = request.getParameter("userName");
        userSex = request.getParameter("userSex");
        userLevel = request.getParameter("userLevel");
        userMajor = request.getParameter("userMajor");
        userClass = request.getParameter("userClass");
        userTime = request.getParameter("userTime");
        userTele = request.getParameter("userTele");
        userEmail = request.getParameter("userEmail");

        StudentInfo si = new StudentInfo(userID,userName,userSex,userLevel,userMajor, userClass,userTime,userTele,userEmail);
        studentDAO sDao = new studentDAO();
        flag = sDao.writeStudentInfo(si);

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
