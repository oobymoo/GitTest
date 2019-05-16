<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style>

span { color: red;}

</style>
</head>
<center>
<body>
<h1 style="color: blue;">欢迎登录北方图书管理系统</h1>
<hr/>

<form action="<%=request.getContextPath()%>/BookController/doAdd" method="post">

	<div>用户编号：<input type="text"  onclick="nomsg()" name="no"/></div><div><span id = "nomsg"></span></div><br/>
	<div>用户id:<input type="text" onclick="idmsg()"  name="id"/></div><div><span id = "idmsg"></span></div><br/>
	<div>输入密码:<input type="text"  onclick="pass()" name="password"/></div><div><span id = "pass"></span></div><br/>
	<div>确认密码：<input type="text"  onclick="pass1()" name = "password1"/></div><div><span id = "pass1"></span></div><br/>
	<div><input type="submit" value="注册"/>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/BookController/goLogin">取消</a></div>
	<span>${msg}</span>

</form>
</body>
</center>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
function nomsg() {
	$("#nomsg").html("编号前三位必须以'100'开头");
}
function idmsg() {
	$("#idmsg").html("用户id前两位必须是字母,长度4到6位数");
	$("#nomsg").html("");
}
function pass() {
	$("#pass").html("用户id前两位必须是字母,长度4到8位数");
	$("#idmsg").html("");
}
function pass1() {
	$("#pass1").html("必须与前一次密码输入一致");
	$("#pass").html("");
}

</script>
</html>