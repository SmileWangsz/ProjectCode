package com.androidservlet;

import com.alibaba.fastjson.JSON;
import com.clientdao.studentDAO;
import com.entity.SchoolClass;
import com.entity.SchoolLevel;
import com.entity.SchoolMajor;
import com.entity.SchoolTime;
import com.util.SchoolClassGroup;
import com.util.SchoolLevelGroup;
import com.util.SchoolMajorGroup;
import com.util.SchoolTimeGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Wangsz on 2017/5/7.
 */
@WebServlet(name = "getSchoolInfoServlet",urlPatterns = "/com/androidservlet/getSchoolInfoServlet")
public class getSchoolInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        int flag = 0;
        int id = 0;

        flag = Integer.parseInt(request.getParameter("flag"));
        id = Integer.parseInt(request.getParameter("id"));

        studentDAO sDao = new studentDAO();

        if(flag == 1)
        {
            ArrayList<SchoolLevel> list = sDao.getAllLevel();
            SchoolLevelGroup group = new SchoolLevelGroup();
            group.setResult(200);
            group.setList(list);

            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);
        }
        if(flag == 2)
        {
            ArrayList<SchoolMajor> list = sDao.getAllMajor(id);
            SchoolMajorGroup group = new SchoolMajorGroup();
            group.setResult(200);
            group.setList(list);

            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);
        }
        if(flag == 3)
        {
            ArrayList<SchoolClass> list = sDao.getAllClass(id);
            SchoolClassGroup group = new SchoolClassGroup();
            group.setResult(200);
            group.setList(list);

            String jsonString = JSON.toJSONString(group);
            PrintWriter out = response.getWriter();
            out.write(jsonString);
        }

        if(flag == 4)
        {
            ArrayList<SchoolTime> list = sDao.getAllTime();
            SchoolTimeGroup group = new SchoolTimeGroup();
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
