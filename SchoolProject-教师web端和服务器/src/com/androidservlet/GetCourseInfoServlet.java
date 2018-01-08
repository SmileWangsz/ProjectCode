package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.dao.selectCourseDao;
import com.entity.Course;
import com.entity.TeacherCourse;
import com.entity.TeacherName;
import com.util.CourseGroup;
import com.util.ShowTeacherCourseGroup;
import com.util.TeacherNameGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/9.
 */
@WebServlet(name = "GetCourseInfoServlet", urlPatterns = "/com/androidservlet/GetCourseInfoServlet")
public class GetCourseInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int flag, id;
        String userId;
        flag = Integer.parseInt(request.getParameter("flag"));

        studentDAO sDao = new studentDAO();
        if(flag == 1)
        {
            id = Integer.parseInt(request.getParameter("courseId"));
            ArrayList<Course> list = sDao.getCourse();
            CourseGroup group = new CourseGroup();
            group.setResult(200);
            group.setList(list);

            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);

        }
        if(flag == 2)
        {
            id = Integer.parseInt(request.getParameter("courseId"));
            String courseId = sDao.getCourseById(id);
            ArrayList<TeacherName> list = sDao.getTeacherName(courseId);
            TeacherNameGroup group = new TeacherNameGroup();
            group.setResult(200);
            group.setList(list);

            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);

        }
        if(flag == 3)
        {
            userId = request.getParameter("userId");

            ArrayList<TeacherCourse> list =sDao.getAllCourse(userId);
            ShowTeacherCourseGroup group = new ShowTeacherCourseGroup();

            group.setResult(200);
            group.setList(list);
            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request,response);
    }
}
