<%@ page import="com.dao.selectCourseDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.entity.QuestionRelease" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.entity.StudentRecord" %>
<%@ page import="com.entity.StudentInfo" %>
<%@ page import="com.clientdao.studentDAO" %><%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/5/13
  Time: 21:58
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
        #show-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #show-img{background: url("image/loginbg.png")}
        #show-a{margin: 40px 0px 0px 10px; width: 180px; height: auto;}
    </style>
</head>
<body>
<div id="show-css">
    <%
        int questionId = Integer.parseInt(request.getParameter("questionID"));
        String courseName = request.getParameter("courseName");
        String courseID = request.getParameter("courseID");
        String newcourseID = request.getParameter("newcourseID");
        String teacherID= request.getSession().getAttribute("loginUserId").toString();
    %>
    <div id="show-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="show-left" style="background: #ababab; float: left; width: 200px; height: 400px">
        <div id="show-a">
            <a style="margin: 10px 0px 20px 0px; display: block" href="checkSubject.jsp?courseID=<%=courseID%>&newcourseID=<%=newcourseID%>">返回习题</a>
            <%
                selectCourseDao sDao = new selectCourseDao();
                ArrayList<QuestionRelease> list = sDao.getAllReleaseRecord(questionId);
                if(list !=null && list.size()>0)
                {
                    for (int i = 0; i < list.size(); i++) {
                        QuestionRelease qr = list.get(i);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                        String date = sdf.format(qr.getStartTime());
            %>
            <a style="margin: 10px 0px 20px 0px; display: block" href="showStudentRecord.jsp?questionID=<%=questionId%>&courseName=<%=courseName%>&releaseId=<%=qr.getReleaseId()%>&courseID=<%=courseID%>&newcourseID=<%=newcourseID%>"><%=date%></a> <%--我这里是链接到show-info div 如果这里成功就解决页面传递好多问题--%>
            <%
                    }
                }
            %>
        </div>
    </div>
    <div id="show-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px; font-family: 宋体;">当前位置：</span>
        <span style="font-size: 1em; font-family: 宋体;"><%=courseName%></span>
        <hr>
    </div>
    <div id="show-info" style="font-family: 宋体; width: 750px; height: 350px; margin-left: 200px; overflow: auto">
        <%
            int releaseId = Integer.parseInt(request.getParameter("releaseId"));
            ArrayList<StudentRecord> listRecord = sDao.getAllStudentRecord(releaseId);

            if(listRecord != null && listRecord.size()>0)
            {
                for (int i = 0; i < listRecord.size(); i++) {
                    StudentRecord sr = listRecord.get(i);
                    StudentInfo si = new studentDAO().getStudentInfo(sr.getStudentId());

        %>
        <div id="show-main" style="width: 700px; height: 28px; margin: 5px 0px 0px 50px;
            overflow: hidden; text-overflow: ellipsis; white-space: nowrap">
            <p style="font-family: 宋体;  height: 28px; margin: 0px 0px 0px 0px;">
                系部：<%=si.getUserLevel()%> 专业：<%=si.getUserMajor()%> 班级：<%=si.getUserTime()%><%=si.getUserClass()%> <%=si.getUserName()%> 答案：<%=sr.getAnswer()%>
            </p>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
