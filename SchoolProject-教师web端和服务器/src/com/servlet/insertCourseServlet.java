package com.servlet;

import com.dao.selectCourseDao;
import com.entity.selectCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wangsz on 2017/4/24.
 */
@WebServlet(name = "insertCourseServlet", urlPatterns = "/com/servlet/insertCourseServlet")
public class insertCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String newCourseID, teacherID, courseID;
        int courseType;
        Date courseTime;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            courseID = request.getParameter("course");
            teacherID = request.getSession().getAttribute("loginUserId").toString();
            newCourseID = teacherID+courseID;
            courseTime = sdf.parse(request.getParameter("time"));
            courseType = 1;//初始建立都是1：待正常状态。 全部编辑完成以后是2：正常状态。0：停课状态
            selectCourse sc = new selectCourse(newCourseID, teacherID,courseID,courseTime,courseType);

            selectCourseDao scDao = new selectCourseDao();
            scDao.insertCourse(sc);
            request.getRequestDispatcher("//editCourse.jsp").forward(request,response);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
