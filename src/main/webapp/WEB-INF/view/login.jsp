<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>北方图书管理系统登录</title>
</head>
<style>
span { color: red;}

</style>
<body>
<center>
<h1 style="color: blue;">欢迎登录北方图书管理系统</h1>
<hr/>
	<form action="<%=request.getContextPath()%>/BookController/doLogin" method="post">
	账号： <input type="text" id = "id" name = "id"/><br/>
	密码： <input  type="password" id = "password" name = "password"/></span><br/>
		<input type="submit" id="dologin" value = "登录" disabled>&nbsp;
		<a href="<%=request.getContextPath()%>/BookController/goAdd">用户注册</a>
		<br/><br/>
		<span id="loginmsg"></span>
		<span>${msg}</span>
	</form>
</center>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">

$("#password").attr("onmouseout","checkall()" );
	function checkall() {
		if($("#id").val() == ""){
			$("#loginmsg").html("账户不能为空");
			 	return;	
		}
		if($("#password").val() == ""){
			$("#dologin").attr("disabled",true);
			$("#loginmsg").html("密码不能为空");
				return;
		}
		$("#dologin").attr("disabled",false);
		
	}

	$("#id").attr("onmousedown","cleanall()");
	function cleanall() {
		$("#loginmsg").html("");
		$("#dologin").attr("disabled",true);
	}



</script>
</html>