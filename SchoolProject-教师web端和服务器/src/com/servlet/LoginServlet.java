package com.servlet;

import com.dao.LoginDao;
import com.entity.TeacherLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wangsz on 2017/4/23.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/com/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static Integer falg = 1;

    public static Integer getFalg() {
        return falg;
    }

    public static void setFalg(Integer falg) {
        LoginServlet.falg = falg;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String username, password;
        username = request.getParameter("username");
        password = request.getParameter("password");

        TeacherLogin tl = new TeacherLogin();
        tl.setUserName(username);
        tl.setPassWord(password);

        LoginDao loginDao = new LoginDao();
        setFalg(loginDao.loginInfo(tl));
        if(falg == 1)
        {
            request.getSession().setAttribute("loginUserId",tl.getUserName());
            request.getRequestDispatcher("//personInfo.jsp").forward(request,response);
        }
        else
        {
            request.getRequestDispatcher("//index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
