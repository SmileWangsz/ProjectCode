<%--
  Created by IntelliJ IDEA.
  User: Wangsz
  Date: 2017/4/20
  Time: 7:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课堂互动</title>
    <style type="text/css">
        #reg-css{width: 955px; height: 244px; margin: 0px 0px 0px 320px;}
        #reg-img{background: url("image/loginbg.png")}
    </style>
    <script type="text/javascript" language="JavaScript">
        function checkName() {
            var regName = document.getElementById("reg-name").value;
            var regTel = document.getElementById("reg-tel").value;
            var regPwd = document.getElementById("reg-pwd").value;
            if(regName == null || regName == "")
            {
                alert("用户名不能为空");
                /*setTimeout(function () {
                 document.getElementById("reg-name").focus();
                },500)*/
                return false;
            }
            if(regTel == null || regTel == "")
            {
                alert("手机号不能为空");
                return false;
            }
            if(regPwd == null || regPwd == "")
            {
                alert("请填写密码，密码不能为空");
                return false;
            }
            else
                return true;
        }
    </script>
</head>
<body>
<div id="reg-css">
    <div id="reg-img">
        <img src="image/logo.png" style="margin: 70px 0px 70px 255px">
    </div>
    <div id="reg-tab">
        <img src="image/jiantou.jpg" style="margin: 10px 0px 0px 5px">
        <span style ="font-size: 1em; color: red; margin: 0px">当前位置：
        </span><span style="font-size: 1em">用户注册</span>
        <hr>
    </div>
    <div id = "reg-info">
        <form name="regForm" action="com/servlet/RegServlet" method="post" onsubmit="return checkName()">
            <label style="margin: 18px 3px 0px 249px; font-size: 16px; font-family: 新宋体">用户名</label>
            <input type="text" name="regName" tabindex="1" placeholder="  设置用户名/请以学号注册" id="reg-name"
                   style="width:260px; height:35px; margin-right: 350px" />
            <label style="margin: 18px 3px 0px 249px; font-size: 16px; font-family: 新宋体" >手机号</label>
            <input type="text" name="regTel" tabindex="2" placeholder="  可用于登录和找回密码" id="reg-tel"
                   style="width:260px; height:35px;margin: 18px 350px 0px 0px"/>
            <label style="margin: 18px 3px 0px 249px; font-size: 16px; font-family: 新宋体">验证码</label>
            <input type="text" name="regCode" tabindex="3" placeholder="  请输入验证码" id="reg-code"
                   style="width:260px; height:35px;margin: 18px 350px 0px 0px"/>
            <label style="margin: 18px 3px 0px 249px; font-size: 16px; font-family: 新宋体">密&nbsp;码</label>
            <input type="text" name="regPwd" tabindex="4" placeholder="  设置用户密码" id="reg-pwd"
                   style="width:260px; height:35px;margin: 18px 350px 0px 0px"/>
            <input type="checkbox" name="isAccept" checked="checked" value="true" style="margin: 18px 3px 0px 304px"/>
            <span style="margin-right: 3px">是否接受协议</span>
            <input type="submit" value="注册" style="width:260px; height:35px; margin: 18px 3px 0px 304px "
                   id="reg-sub"/>
        </form>
    </div>
</div>
</body>
</html>