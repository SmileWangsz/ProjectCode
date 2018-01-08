<%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/24
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dao.selectCourseDao" %>
<%@ page import="com.dao.questionDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.entity.courseQuestion" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>课堂互动</title>
    <base href="<%=basePath%>">
    <style type="text/css">
        #check-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #check-img{background: url("image/loginbg.png")}
        #left-main{margin: 40px 20px 0px 40px; width: 80px; height: auto;}
        #check-info a{
            color: #b22222;
            text-decoration: none;
        }
        #check-info a:hover, a:active{
            color: #912cee;
            text-decoration: none;
        }
        #check-main a{
            color: #6495ed;
            text-decoration: none;
        }
        #check-main a:hover, a:active{
            color: #cdad00;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div id="check-css">
    <div id="check-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="check-left" style="background: #ababab; float: left; width: 150px; height: 400px">
        <div id="left-main">
            <jsp:include page="left1.jsp"></jsp:include>
        </div>
    </div>
    <%
        String courseID = request.getParameter("courseID");
        String newcourseID = request.getParameter("newcourseID");
        selectCourseDao scDao = new selectCourseDao();
        String courseName = scDao.getCourseById(courseID);
        int flag = -1;
        //request.getSession().setAttribute("courseName",courseName);
    %>
    <div id="check-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px; font-family: 宋体;">当前位置：</span>
        <span style="font-size: 1em; font-family: 宋体;"><%=courseName%></span>
        <span style="font-size: 1em; font-family: 宋体;">查看习题</span>
        <hr>
    </div>
    <div id="check-info" style="font-family: 宋体; width: 800px; height: 400px; margin-left: 150px; overflow: auto">
        <a style="font-family: 宋体; margin: 5px 0px 0px 300px" href="editSubject.jsp?courseID=<%=courseID%>&newcourseID=<%=newcourseID%>&questionID=<%=flag%>">点击添加习题</a>
        <%
            questionDao qDAO = new questionDao();
            String teacherID= request.getSession().getAttribute("loginUserId").toString();
            ArrayList<courseQuestion> list =qDAO.getAllQuestion(newcourseID,teacherID);
            String type = null;
            if(list != null && list.size()>0)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    courseQuestion cq = list.get(i);
                    if(cq.getQuestionType() == -1)
                    {
                        continue;
                    }
                    if(cq.getQuestionType() == 0)
                    {
                        type= "发布";
                    }
                    if(cq.getQuestionType() == 1)
                    {
                        type = "取消发布";
                    }
        %>
        <div id="check-main" style="width: 600px; height: 28px; margin: 5px 0px 0px 100px;
            overflow: hidden; text-overflow: ellipsis; white-space: nowrap">

            <a style="font-family: 宋体;  height: 28px; margin: 0px 5px 0px 0px; " href="editSubject.jsp?courseID=<%=courseID%>&newcourseID=<%=newcourseID%>&questionID=<%=cq.getQuestionID()%>">
                修改
            </a>
            <a style="font-family: 宋体; height: 28px; margin: 0px 5px 0px 0px;" href="com/servlet/updateTypeServlet?questionID=<%=cq.getQuestionID()%>&questionType=<%=cq.getQuestionType()%>&courseID=<%=courseID%>&action=update">
                <%=type%>
            </a>
            <a style="font-family: 宋体; height: 28px; margin: 0px 5px 0px 0px;" href="com/servlet/updateTypeServlet?questionID=<%=cq.getQuestionID()%>&questionType=<%=cq.getQuestionType()%>&courseID=<%=courseID%>&action=delete">
                删除
            </a>
            <a style="font-family: 宋体; height: 28px; margin: 0px 5px 0px 0px;" href="showStudentRecord.jsp?questionID=<%=cq.getQuestionID()%>&courseName=<%=courseName%>&releaseId=0&courseID=<%=courseID%>&newcourseID=<%=newcourseID%>">
                查看
            </a>
            <span style="font-family: 华文楷体; font-size: 16px; color: #8b8989; display: inline-block;">
                    第<%=cq.getZhanID()%>章 第<%=cq.getJieID()%>节 知识点：<%=cq.getQuestionPoint()%>
                    内容：<%=cq.getQuestionContent()%>
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
