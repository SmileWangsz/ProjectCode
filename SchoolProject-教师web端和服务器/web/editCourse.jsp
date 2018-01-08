<%@ page import="java.util.ArrayList" %>
<%@ page import="com.entity.selectCourse" %>
<%@ page import="com.dao.selectCourseDao" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/24
  Time: 0:58
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
        #edit-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #edit-img{background: url("image/loginbg.png")}
        #left-main{margin: 40px 20px 0px 40px; width: 80px; height: auto;}
    </style>
</head>
<body>
<div id="edit-css">
    <div id="edit-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="edit-left" style="background: #ababab; float: left; width: 150px; height: 400px">
        <div id="left-main">
            <jsp:include page="left.jsp"></jsp:include>
        </div>
    </div>
    <div id="edit-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px; font-family: 宋体;">当前位置：
        </span><span style="font-size: 1em; font-family: 宋体;">编辑课程</span>
        <hr>
    </div>
    <div style="font-family: 宋体; width: 705px; height: 450px; margin-left: 200px;">
        <%
            String id = request.getSession().getAttribute("loginUserId").toString();
            selectCourseDao scDao = new selectCourseDao();
            ArrayList<selectCourse> list = scDao.getAllCourse(id);
            if(list != null && list.size() > 0 )
            {
                for (int i = 0; i < list.size(); i++) {

                    selectCourse sc = list.get(i);
                    if(sc.getCourseType() == 0)
                    {
                        continue;
                    }
        %>
        <div style="margin: 5px; font-family: 宋体; width: 210px; height: 170px; padding: 4px; float: left">
            <h3 align="center">课程</h3>
            <a style="margin: 5px 19px 0px 20px; font-family: 宋体; display: block; text-align: center" onclick="" href="checkSubject.jsp?courseID=<%=sc.getCourseID()%>&newcourseID=<%=sc.getNewcourseID()%>">
                <%=scDao.getCourseById(sc.getCourseID())%>
            </a>
            <span style="margin: 20px 0px 0px 50px; display: block; font-family: 宋体; color: #a3a3a3;">
                <%
                    String type = null;

                    if(sc.getCourseType() == 1)
                    {
                        type = "编辑状态";
                    }
                    if(sc.getCourseType() == 2)
                    {
                        type = "正常状态";
                    }
                %>
                课程状态：<%=type%>
            </span>
            <span style="margin: 20px 0px 0px 8px; display: block; font-family: 宋体; color: #6b8e23;">开课时间：
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                    String date = sdf.format(sc.getCourseTime());
                %>
                <%=date%>
            </span>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>