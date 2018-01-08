package com.androidservlet;

import com.clientdao.studentDAO;
import com.entity.StudentCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Wangsz on 2017/5/10.
 */
@WebServlet(name = "WriteStudentCourseServlet",urlPatterns = "/com/androidservlet/WriteStudentCourseServlet")
public class WriteStudentCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String newCourseId, studentId, teacherId;
        int courseId, courseType;
        boolean insertFlag = false, updateFlag = false, selectFlag = false;

        newCourseId = request.getParameter("newCourseId");
        studentId = request.getParameter("studentId");
        courseId = Integer.parseInt(request.getParameter("courseId"));
        teacherId = request.getParameter("teacherId");
        courseType = Integer.parseInt(request.getParameter("courseType"));
        studentDAO sDao = new studentDAO();

        selectFlag = sDao.selectCourseName(newCourseId);

        if (selectFlag)
        {
            updateFlag = sDao.updateCourseType(newCourseId);
            PrintWriter out = response.getWriter();
            if (updateFlag)
            {
                out.write("success");
            }
            else
                out.write("failure");
        }

        else
        {
            StudentCourse sc = new StudentCourse(newCourseId, studentId, courseId, teacherId,courseType);

            insertFlag = sDao.writeStudentCourse(sc);
            PrintWriter out = response.getWriter();

            if(insertFlag)
            {
                out.write("success");
            }
            else
                out.write("failure");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
