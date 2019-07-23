<%@ page import="util.StringUtil" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/15
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统登录</title>
    <script>
         function  resetValue() {
             document.getElementById("username").value="";
             document.getElementById("password").value="";
         }

    </script>
</head>
<body>

<div align="center" style="padding-top: 50px;" >
    <form action="login" method="post">
    <table style="width: 740px;height: 500px" background="images/login.jpg" >
        <tr height="180">
            <td colspan="4"></td>
        </tr>
        <tr height="10">
            <td width="40%"></td>
            <td width="10%">用户名</td>
            <td><input type="text" value="${username}" name="username" id="username">
            <td width="30%"></td>
        </tr>
        <tr height="10">
            <td width="40%"></td>
            <td width="10%">密&nbsp;&nbsp;&nbsp;码</td>
            <td><input type="password" value="${password}" name="password" id="password">
            <td width="30%"></td>
        </tr>
        <tr height="20" valign="center">
            <td width="40%"></td>
            <td style=" width:10%; " align="right"><input style="margin-left: 15px;margin-top: 5px" type="submit"
                                                          value="登录"></td>
            <td><input style="margin-left: 15px;margin-top: 5px" type="button" value="重置" onclick="resetValue()">
            <td width="30%"></td>
        </tr>
        <tr height="10">
            <td width="40%"></td>
            <td colspan="3"><font color="red">${error}</font></td>
        </tr>
        <tr>
            <td></td>
        </tr>
    </table>
    </form>
</div>

</body>
</html>
