package com.servlet;

import com.dao.WriteInfoDao;
import com.entity.TeacherReg;
import com.entity.WriteTeacherInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wangsz on 2017/4/23.
 */
@WebServlet(name = "WriteInfoServlet", urlPatterns = "/com/servlet/WriteInfoServlet")
public class WriteInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String userId, userName, gender,teleNumber,email;
        Date time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            userId = (String) request.getSession().getAttribute("loginUserId");
            userName = request.getParameter("userName");
            gender = request.getParameter("gender");
            teleNumber = request.getParameter("telphone");
            email = request.getParameter("email");
            time = sdf.parse(request.getParameter("time"));

            WriteTeacherInfo wti = new WriteTeacherInfo();
            wti.setUser_Id(userId);
            wti.setUserName(userName);
            wti.setGender(gender);
            wti.setTelNumber(teleNumber);
            wti.setEmail(email);
            wti.setTime(time);

            WriteInfoDao writeInfoDao = new WriteInfoDao();
            writeInfoDao.writeInfo(wti);

            request.getRequestDispatcher("//personInfo.jsp").forward(request,response);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
