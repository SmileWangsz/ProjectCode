<%@ page import="com.dao.WriteInfoDao" %>
<%@ page import="com.entity.WriteTeacherInfo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/21
  Time: 8:36
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
        #per-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #per-img{background: url("image/loginbg.png")}
        #left-main{margin: 40px 20px 0px 40px; width: 80px; height: auto;}
    </style>
</head>
<body>
<div id="per-css">
    <div id="per-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="per-left" style="background: #ababab; float: left; width: 150px; height: 400px">
        <div id="left-main">
            <jsp:include page="left.jsp"></jsp:include>
        </div>
    </div>
    <div id="per-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px; font-family: 宋体;">当前位置：
        </span><span style="font-size: 1em; font-family: 宋体;">个人信息</span>
        <hr>
    </div>
    <div id="per-info" style="font-family: 宋体; width: 600px; margin-left: 150px;">
        <table width="400" style="border-collapse: separate; border-spacing: 0px 10px; margin: 25px 0px 0px 35px" align="center">

            <%
                WriteInfoDao writeInfoDao = new WriteInfoDao();
                WriteTeacherInfo wti = writeInfoDao.readInfo(request.getSession().getAttribute("loginUserId").toString());
                if(wti !=null)
                {
            %>
            <tr>
                <td style="width: 30%">&nbsp;&nbsp;姓&nbsp;&nbsp;名:</td>
                <td style="width: 70%"><%=wti.getUserName()%></td>
            </tr>
            <tr>
                <td style="width: 30%">&nbsp;&nbsp;年&nbsp;&nbsp;龄：</td>
                <td style="width: 70%">20</td>
            </tr>
            <tr>
                <td style="width: 30%">&nbsp;&nbsp;性&nbsp;&nbsp;别：</td>
                <td style="width: 70%"><%=wti.getGender()%></td>
            </tr>
            <tr>
                <td style="width: 30%">&nbsp;&nbsp;入职时间：</td>
                <td style="width: 70%">
                    <%
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                        String date = sdf.format(wti.getTime());
                    %>
                    <%=date%>
                </td>
            </tr>
            <tr>
                <td style="width: 30%">&nbsp;&nbsp;联系电话：</td>
                <td style="width: 70%"><%=wti.getTelNumber()%></td>
            </tr>
            <tr>
                <td style="width: 30%">&nbsp;&nbsp;E-mail：</td>
                <td style="width: 70%"><%=wti.getEmail()%></td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="2">
                    <input type="submit" value="修改" id="per-sub" style="width:100px; margin-left: 60px"/>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
