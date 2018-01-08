package com.servlet;

import com.dao.RegisterDao;
import com.entity.TeacherReg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wangsz on 2017/4/22.
 */
@WebServlet(name = "RegServlet", urlPatterns = "/com/servlet/RegServlet")
public class RegServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String username, password, telephone;

        username = request.getParameter("regName");
        password = request.getParameter("regPwd");
        telephone = request.getParameter("regTel");

        TeacherReg tr = new TeacherReg();
        tr.setUsername(username);
        tr.setPassword(password);
        tr.setTelenumber(telephone);
        request.getSession().setAttribute("loginUserId", tr.getUsername());
        RegisterDao rsDao = new RegisterDao();
        rsDao.regInfo(tr);
        request.getRequestDispatcher("//stuWriteInfo.jsp").forward(request,response);
    }

    public RegServlet() {
    }
}
