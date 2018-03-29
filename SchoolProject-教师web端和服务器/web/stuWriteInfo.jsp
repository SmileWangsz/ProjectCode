<%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/20
  Time: 16:07
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
        #swi-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #swi-img{background: url("image/loginbg.png")}
        #swi-info{width: 400px; height: auto; margin: 5px 0px 0px 249px}
    </style>
    <script type="text/javascript" src="js/Calendar3.js"></script>
</head>
<body>
<div id="swi-css">
    <div id="swi-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="swi-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 100px">
        <span style ="font-size: 1em; color: red; margin: 0px">当前位置：
        </span><span style="font-size: 1em">完善信息</span>
        <hr>
    </div>
    <div id="swi-info">
        <form name="swiForm" action="com/servlet/WriteInfoServlet" method="post">
            <label style="margin: 20px 3px 0px 0px; font-size: 16px; font-family: 新宋体">&nbsp;姓&nbsp;名</label>
            <input type="text" name="userName" tabindex="1" placeholder="  请输入真实姓名" id="swi-name"
                   style="width:260px; height:35px; margin-top:10px;margin-right: 50px" />

            <label style="margin: 20px 3px 0px 0px; font-size: 16px; font-family: 新宋体">&nbsp;性&nbsp;别</label>
            <input type="radio" name="gender" value="Male" style="margin-top:10px; margin-left: 10px" />男
            <input type="radio" name="gender" value="FeMale" style="margin: 0px 0px 0px 10px" />
            <span style="margin-right: 220px">女</span>

            <label style="margin: 20px 3px 0px 0px; font-size: 16px; font-family: 新宋体">入职时间</label>
            <input type="text" name="time" tabindex="2" placeholder="  填写你的入职时间" id="swi-time"
                   style="width:260px; height:35px; margin-top:10px;margin-right: 50px" onclick="new Calendar().show(this)" readonly="readonly"/>

            <label style="margin: 20px 3px 0px 0px; font-size: 16px; font-family: 新宋体">手机号码</label>
            <input type="text" name="telphone" tabindex="3" placeholder="  请留下的你联系方式" id="swi-tel"
                   style="width:260px; height:35px; margin-top:10px;margin-right: 50px" />

            <label style="margin: 20px 3px 0px 0px; font-size: 16px; font-family: 新宋体">&nbsp;邮&nbsp;箱</label>
            <input type="text" name="email" tabindex="4" placeholder="  请留下的你的邮箱" id="swi-email"
                   style="width:260px; height:35px; margin-top:10px;margin-right: 50px" />

            <input type="submit" value="保存" id="swi-sub"
                   style="width: 260px; height: 35px; margin: 10px 50px 0px 70px"/>
        </form>
    </div>
</div>
</body>
</html>
