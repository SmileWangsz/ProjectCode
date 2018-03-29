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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Wangsz on 2017/5/12.
 */
@WebServlet(name = "QuestionReleaseServlet", urlPatterns = "/com/androidservlet/QuestionReleaseServlet")
public class QuestionReleaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int questionId;
        int peopleNumber;
        int releaseId;
        Timestamp startTime;
        boolean flag = false;

        Date date = new Date();
        //String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        startTime = new Timestamp(date.getTime());//Timestamp.valueOf(dateNow);
        questionId = Integer.parseInt(request.getParameter("questionId"));
        peopleNumber = Integer.parseInt(request.getParameter("peopleNumber"));
        releaseId = new Random().nextInt(100000000);

        studentDAO sDao = new studentDAO();
        flag = sDao.updateQuestionType(questionId, 2, releaseId);
        PrintWriter out = response.getWriter();
        if(flag)
        {
            flag = sDao.setQuestionRelease(releaseId, questionId, startTime, peopleNumber);
            if(flag)
            {
                out.write(String.valueOf(releaseId));
            }else
                out.write("");

        }else
            out.write("");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
