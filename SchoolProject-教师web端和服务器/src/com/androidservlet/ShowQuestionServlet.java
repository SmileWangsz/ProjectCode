package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.entity.courseQuestion;
import com.util.ShowQuestionGroup;

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
@WebServlet(name = "ShowQuestionServlet", urlPatterns = "/com/androidservlet/ShowQuestionServlet")
public class ShowQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String newCourseId;
        newCourseId = request.getParameter("newCourseId");

        studentDAO sDao = new studentDAO();

        courseQuestion cq = sDao.getQuestionById(newCourseId);
        if(cq !=null)
        {
            ShowQuestionGroup group = new ShowQuestionGroup();
            group.setResult(200);
            group.setCq(cq);

            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);
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
