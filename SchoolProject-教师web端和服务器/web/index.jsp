<%@ page import="com.servlet.LoginServlet" %><%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/19
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>课堂互动</title>
      <base href="<%=basePath%>">
      <style type="text/css">
          #main{width: 900px; height: 501px; margin: 9% 0px 0px 17%}
          #user-pic{width: 510px;  height: 488px;  float: left; padding: 10px; margin: 0px ;
              background: url("image/loginbg.png");}
          #login-part{width: 320px;  height: 488px;  float: right; padding: 10px; margin: 0px; border: 1px solid #bbbbbb;
              display: block;}
          #msg-info{width: 350px; height: 20px; margin: 20px 0px 0px 24px; font-size: 14px;}
      </style>
  </head>
  <body>
  <div id="main">

      <div id="user-pic">
          <img src="image/logo.png" alt="" style="margin: 10% 0px 0px 13%">
      </div>

      <div id="login-part">

          <p style="margin: 60px 0px 0px 25px">账号登录</p>

          <form name="loginForm" action="com/servlet/LoginServlet" method="post">

              <div class="user-info">

                  <input type="text" name="username" tabindex="1" placeholder="输入用户名" value="" id="username-info"
                         style="margin: 25px; width:249px; height:35px;"/>

                  <input type="password" name="password" tabindex="2" placeholder="输入密码" value="" id="pass-info"
                  style="margin-left: 25px; width:249px; height:35px;"/>

                  <div id = "msg-info">

                      <input type="checkbox" name="isUserCookie" checked="checked" id="check-info" />记住我的登录状态

                      <a href="reg.jsp" style="margin-left: 50px">立即注册</a>

                  </div>

                  <input type="submit" value="登录" id="sub-info" style="width:249px;height:35px; margin: 25px 0px 0px 25px"/>

                  <%
                      LoginServlet ls = new LoginServlet();
                      if(ls.getFalg() == 0)
                      {
                  %>
                  <div style="margin: 20px 0px 0px 70px">
                      <span style="font-family: 宋体; color: red;">用户名或者密码错误</span>
                  </div>
                  <%
                      }
                  %>


              </div>

          </form>
      </div>
  </div>
  </body>
</html>
