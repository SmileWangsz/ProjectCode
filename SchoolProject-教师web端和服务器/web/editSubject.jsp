<%@ page import="com.dao.selectCourseDao" %>
<%@ page import="com.dao.questionDao" %>
<%@ page import="com.entity.courseQuestion" %><%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/24
  Time: 15:55
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
        #editS-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #editS-img{background: url("image/loginbg.png")}
        #left-main{margin: 40px 20px 0px 40px; width: 80px; height: auto;}
    </style>
</head>
<body>
<div id="editS-css">
    <div id="editS-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="editS-left" style="background: #ababab; float: left; width: 150px; height: 400px">
        <div id="left-main">
            <jsp:include page="left1.jsp"></jsp:include>
        </div>
    </div>
    <%
        String courseID = request.getParameter("courseID");
        String newcourseID = request.getParameter("newcourseID");
        String teacherID= request.getSession().getAttribute("loginUserId").toString();
        request.getSession().setAttribute("newcourseID",newcourseID);
        request.getSession().setAttribute("courseID",courseID);
        selectCourseDao scDao = new selectCourseDao();
        String courseName = scDao.getCourseById(courseID);
        int questionID = Integer.parseInt(request.getParameter("questionID"));
        request.getSession().setAttribute("questionID",questionID);
    %>
    <div id="editS-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px; font-family: 宋体;">当前位置：</span>
        <span style="font-size: 1em; font-family: 宋体;color: cornflowerblue"><%=courseName%>  </span>
        <span style="font-size: 1em; font-family: 宋体;">编辑习题</span>
        <hr>
    </div>
    <div id="editS-info" style="font-family: 宋体; width: 805px; height:400px;margin-left: 150px;">
        <%
            if (questionID==-1)
            {
        %>
        <form action="com/servlet/insertQuestionServlet" name="editSubjectForm" method="post">
            <div id="editS-main" style="width: 500px; height:400px; margin: 10px 0px 0px 140px">
                <span style="font-family: 华文楷体; font-size: 18px;">第</span>
                <select  name="zhan" style="width: 40px; height: 28px; margin: 0px 0px 0px 10px">
                    <%
                        for (int i = 1; i < 20; i ++)
                        {
                    %>
                    <option value=<%=i%>><%=i%></option>
                    <%
                        }
                    %>
                </select>
                <span style="font-family: 华文楷体; font-size: 18px;">章</span>
                <select  name="jie" style="width: 40px; height: 28px; margin: 0px 0px 0px 10px">
                    <%
                        for (int i = 1; i < 20; i ++)
                        {
                    %>
                    <option value=<%=i%>><%=i%></option>
                    <%
                        }
                    %>
                </select>
                <span style="font-family: 华文楷体; font-size: 18px;">节</span>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 0px 0px 0px 5px">知识点：</span>
                <input type="text" name="kenPoint" value="" placeholder="填写关键知识点" style="width: 220px; height: 28px"/>
                <div style="height: 60px; width: 100px; float: left">
                    <span style="font-family: 华文楷体; font-size: 18px; margin: 30px 0px 0px 5px; display: inline-table">习题内容：</span>
                </div>
                <textarea name="kenContent" style="width: 400px; height: 60px; margin: 10px 0px 0px 0px" ></textarea>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">A：</span>
                <input type="text" name="keyA" value="" placeholder="填写选项内容" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">B：</span>
                <input type="text" name="keyB" value="" placeholder="填写选项内容" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">C：</span>
                <input type="text" name="keyC" value="" placeholder="填写选项内容" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">D：</span>
                <input type="text" name="keyD" value="" placeholder="填写选项内容" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 5px;display: inline-table">正确答案：</span>
                <select  name="rightKey" style="width: 55px; height: 28px; margin: 0px 300px 0px 0px">
                    <option value="A" >A</option>
                    <option value="B" >B</option>
                    <option value="C" >C</option>
                    <option value="D" >D</option>
                </select>
                <input id="editS-sub" type="submit" align="center" value="保存"
                       style="width: 200px; height: 30px; margin: 20px 0px 0px 150px">
                <a style="font-family: 宋体; color: #6495ed; font-size: 15px"
                   href="checkSubject.jsp?courseID=<%=courseID%>&newcourseID=<%=newcourseID%>">
                    放弃编辑
                </a>
            </div>
        </form>
        <%
            }
            else
            {
                questionDao qDao = new questionDao();
                courseQuestion cq = qDao.getQuestionById(questionID);
        %>
        <form action="com/servlet/updateQuestionServlet" name="editSubjectForm" method="post">
            <div style="width: 500px; height:400px; margin: 10px 0px 0px 140px">
                <span style="font-family: 华文楷体; font-size: 18px;">第</span>
                <select  name="zhan" style="width: 40px; height: 28px; margin: 0px 0px 0px 10px">
                    <option value=<%=cq.getZhanID()%>><%=cq.getZhanID()%></option>
                    <%
                        for (int i = 1; i < 20; i ++)
                        {
                    %>
                    <option value=<%=i%>><%=i%></option>
                    <%
                        }
                    %>
                </select>
                <span style="font-family: 华文楷体; font-size: 18px;">章</span>
                <select  name="jie" style="width: 40px; height: 28px; margin: 0px 0px 0px 10px">

                    <option value=<%=cq.getJieID()%>><%=cq.getJieID()%></option>
                    <%
                        for (int i = 1; i < 20; i ++)
                        {
                    %>
                    <option value=<%=i%>><%=i%></option>
                    <%
                        }
                    %>

                </select>
                <span style="font-family: 华文楷体; font-size: 18px;">节</span>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 0px 0px 0px 5px">知识点：</span>
                <input type="text" name="kenPoint" value="<%=cq.getQuestionPoint()%>" style="width: 220px; height: 28px"/>
                <div style="height: 60px; width: 100px; float: left">
                    <span style="font-family: 华文楷体; font-size: 18px; margin: 30px 0px 0px 5px; display: inline-table">习题内容：</span>
                </div>
                <textarea name="kenContent" style="width: 400px; height: 60px; margin: 10px 0px 0px 0px" ><%=cq.getQuestionContent()%></textarea>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">A：</span>
                <input type="text" name="keyA" value="<%=cq.getKeyA()%>" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">B：</span>
                <input type="text" name="keyB" value="<%=cq.getKeyB()%>" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">C：</span>
                <input type="text" name="keyC" value="<%=cq.getKeyC()%>" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 60px;display: inline-table">D：</span>
                <input type="text" name="keyD" value="<%=cq.getKeyD()%>" style="width: 400px; height: 28px"/>
                <span style="font-family: 华文楷体; font-size: 18px; margin: 10px 0px 0px 5px;display: inline-table">正确答案：</span>
                <select  name="rightKey" style="width: 55px; height: 28px; margin: 0px 300px 0px 0px">
                    <option value=<%=cq.getRightKey()%> ><%=cq.getRightKey()%></option>
                    <option value="A"> A</option>
                    <option value="B"> B</option>
                    <option value="C"> C</option>
                    <option value="D"> D</option>
                </select>
                <input type="submit" align="center" value="保存"
                       style="width: 200px; height: 30px; margin: 20px 0px 0px 150px">
            </div>
        </form>
        <%
            }
        %>
    </div>
</div>
</body>
</html>