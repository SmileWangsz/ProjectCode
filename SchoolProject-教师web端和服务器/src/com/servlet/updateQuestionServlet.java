package com.servlet;

import com.dao.questionDao;
import com.entity.courseQuestion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wangsz on 2017/4/26.
 */
@WebServlet(name = "updateQuestionServlet", urlPatterns = "/com/servlet/updateQuestionServlet")
public class updateQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int zhanID,jieID,questionType,questionID;
        String newcourseID,questionPoint,questionContent,
                keyA,keyB,keyC,keyD,rightKey,courseID,teacherID;

        questionID = Integer.parseInt(request.getSession().getAttribute("questionID").toString());
        newcourseID = request.getSession().getAttribute("newcourseID").toString();
        teacherID = request.getSession().getAttribute("loginUserId").toString();
        courseID = request.getSession().getAttribute("courseID").toString();
        //System.out.println(newcourseID);
        zhanID = Integer.parseInt(request.getParameter("zhan"));
        jieID = Integer.parseInt(request.getParameter("jie"));
        questionType = 0;
        questionPoint = request.getParameter("kenPoint");
        questionContent = request.getParameter("kenContent");
        questionContent.replaceAll("\r|\n","");
        keyA = request.getParameter("keyA");
        keyB = request.getParameter("keyB");
        keyC = request.getParameter("keyC");
        keyD = request.getParameter("keyD");
        rightKey = request.getParameter("rightKey");

        courseQuestion cq = new courseQuestion(newcourseID,teacherID,zhanID,jieID,questionType,questionPoint,
                questionContent,keyA,keyB,keyC,keyD,rightKey);

        questionDao qDao = new questionDao();
        qDao.updateQuestion(cq,questionID);
        request.getRequestDispatcher("//checkSubject.jsp?courseID="+courseID+"&newcourseID="+newcourseID).
                forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
