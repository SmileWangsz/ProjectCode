package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.entity.StudentInfo;
import com.util.StudentInfoGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wangsz on 2017/5/9.
 */
@WebServlet(name = "GetStudentInfoServlet",urlPatterns = "/com/androidservlet/GetStudentInfoServlet")
public class GetStudentInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String userId, userName, userLevel, userMajor, userClass;

        userId = request.getParameter("userId");

        studentDAO sDao = new studentDAO();

        StudentInfo si = sDao.getStudentInfo(userId);
        userName = si.getUserName();
        userLevel = si.getUserLevel();
        userMajor = si.getUserMajor();
        userClass = si.getUserClass();

        StudentInfoGroup group = new StudentInfoGroup();
        group.setResult(200);
        group.setUserName(userName);
        group.setUserLevel(userLevel);
        group.setUserMajor(userMajor);
        group.setUserClass(userClass);

        String jsonString = JSON.toJSONString(group);
        PrintWriter out = response.getWriter();
        out.write(jsonString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}

