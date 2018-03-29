package com.androidservlet;

import com.clientdao.studentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by Wangsz on 2017/5/12.
 */
@WebServlet(name = "QuestionReleaseOverServlet", urlPatterns = "/com/androidservlet/QuestionReleaseOverServlet")
public class QuestionReleaseOverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int questionId;
        boolean flag = false;

        questionId = Integer.parseInt(request.getParameter("questionId"));

        studentDAO sDao = new studentDAO();
        flag = sDao.updateQuestionType(questionId, 1, 0);
        PrintWriter out = response.getWriter();
        if(flag)
        {
            out.write("success");

        }else
            out.write("failure");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
