package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.entity.courseQuestion;
import com.util.ShowCourseAllQuestionGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/12.
 */
@WebServlet(name = "readCourseAllQuestionServlet", urlPatterns = "/com/androidservlet/readCourseAllQuestionServlet")
public class readCourseAllQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String newCourseId;
        newCourseId = request.getParameter("newCourseId");

        studentDAO sDao = new studentDAO();
        ArrayList<courseQuestion> list = sDao.getAllQuestionById(newCourseId);

        ShowCourseAllQuestionGroup group = new ShowCourseAllQuestionGroup();
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

