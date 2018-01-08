<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.util.DBHelper" %><%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/23
  Time: 22:37
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
        #sele-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #sele-img{background: url("image/loginbg.png")}
        #left-main{margin: 40px 20px 0px 40px; width: 80px; height: auto;}
    </style>
    <script type="text/javascript" src="js/Calendar3.js"></script>
</head>
<body>
<div id="sele-css">
    <div id="sele-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="sele-left" style="background: #ababab; float: left; width: 150px; height: 400px">
        <div id="left-main">
            <jsp:include page="left.jsp"></jsp:include>
        </div>
    </div>
    <div id="sele-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px; font-family: 宋体;">当前位置：
        </span><span style="font-size: 1em; font-family: 宋体;">选择课程</span>
        <hr>
    </div>
    <div id="sele-info" style="font-family: 宋体; width: 700px; height: 200px; margin-left: 150px;">
        <form name="selectCourseForm" action="com/servlet/insertCourseServlet" method="post">
            <span style="font-size: 1em; font-family: 宋体; margin: 60px 0px 0px 110px; color: cornflowerblue">选取课程</span>
            <select name = "course" style="width: 180px; height: 28px; margin: 60px 0px 0px 10px">
                <%
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;

                    conn = DBHelper.getConnection();
                    String sql = "select * From course";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    while(rs.next())
                    {
                %>
                <option value=<%=rs.getInt("course_Id")%>><%=rs.getString("course_Name")%></option>
                <%
                    }
                    rs.close();
                    stmt.close();
                    //conn.close();
                %>
            </select>
            <span style="font-size: 1em; font-family: 宋体; margin: 60px 0px 0px 20px; color: cornflowerblue">开课时间</span>
            <input type="text" name="time" tabindex="2" placeholder="  编辑您的开课时间" id="sele-time"
                   style="width:200px; height:28px;" onclick="new Calendar().show(this)" readonly="readonly"/>

            <input type="submit" value="保存" id="sele_sub" style="width: 220px; height: 30px; margin: 30px 0px 0px 280px"/>
        </form>
    </div>
</div>
</body>
</html>
