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
 * Created by Wangsz on 2017/5/13.
 */
@WebServlet(name = "SubmitStudentRecoderServlet", urlPatterns = "/com/androidservlet/SubmitStudentRecoderServlet")
public class SubmitStudentRecoderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int questionId, releaseId;
        String answer, studentId;
        boolean flag;

        questionId = Integer.parseInt(request.getParameter("questionId"));
        answer = request.getParameter("answer");
        studentId = request.getParameter("studentId");

        studentDAO sDao = new studentDAO();
        releaseId = sDao.getReleaseId(questionId);
        PrintWriter out = response.getWriter();
        if(releaseId != 0)
        {
            String recordId = String.valueOf(releaseId)+studentId;
            flag = sDao.setStudentRecord(recordId,studentId, releaseId, answer);
            if(flag)
            {
                out.write("success");
            }
            else
                out.write("redo");//用来判断是否二次进行答题，如果是二次，因为ID唯一，数据表注入失败。
        }
        else
            out.write("failure");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
