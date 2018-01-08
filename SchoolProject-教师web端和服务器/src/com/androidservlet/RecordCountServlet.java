package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.util.ResultGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wangsz on 2017/5/14.
 */
@WebServlet(name = "RecordCountServlet", urlPatterns = "/com/androidservlet/RecordCountServlet")
public class RecordCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int releaseId, peopleNumber;
        String rightKey;

        rightKey = request.getParameter("rightKey");
        releaseId = Integer.parseInt(request.getParameter("releaseId"));
        peopleNumber = Integer.parseInt(request.getParameter("peopleNumber"));

        int sumA, sumB, sumC, sumD, rightSum, sumAll;
        double accuracy;//正确率

        studentDAO sDao = new studentDAO();
        sumA = sDao.getStudentAnswerSum(releaseId,"A");
        sumB = sDao.getStudentAnswerSum(releaseId,"B");
        sumC = sDao.getStudentAnswerSum(releaseId,"C");
        sumD = sDao.getStudentAnswerSum(releaseId,"D");
        rightSum = sDao.getStudentAnswerSum(releaseId,rightKey);
        sumAll = sumA+sumB+sumC+sumD;
        accuracy =  ((double)rightSum/(double)peopleNumber)*100;

        ResultGroup group = new ResultGroup(200, sumA,sumB,sumC,sumD,sumAll,accuracy);

        String jsonString = JSON.toJSONString(group);
        PrintWriter out = response.getWriter();
        out.write(jsonString);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
