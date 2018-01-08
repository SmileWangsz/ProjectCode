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
 * Created by Wangsz on 2017/4/27.
 */
@WebServlet(name = "updateTypeServlet",urlPatterns = "/com/servlet/updateTypeServlet")
public class updateTypeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        int questionID, questionType;
        String courseID, action;

        questionID = Integer.parseInt(request.getParameter("questionID"));
        questionType = Integer.parseInt(request.getParameter("questionType"));
        courseID = request.getParameter("courseID");
        action = request.getParameter("action");
        questionDao qDao = new questionDao();

        if(action.equals("update"))
        {
            if(questionType == 0)
            {
                qDao.updateType(questionID,1);
            }
            if(questionType == 1)
            {
                qDao.updateType(questionID,0);
            }
        }
        if(action.equals("delete"))
        {
            qDao.updateType(questionID, -1);
        }
        courseQuestion cq = qDao.getQuestionById(questionID);
        String newcourseID = cq.getNewcourseID();

        request.getRequestDispatcher("//checkSubject.jsp?courseID="+courseID+"&newcourseID="+newcourseID).
                forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
