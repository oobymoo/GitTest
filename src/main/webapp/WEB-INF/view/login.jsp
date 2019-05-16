<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
		boolean check=false;
	Cookie[] cookies= request.getCookies();
	if(cookies!=null&&cookies.length>0){
		for(Cookie cookie:cookies){
			if("wtbNameCookie".equals(cookie.getName())){
				check=true;
			}
		}
	}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乌托邦登录页</title>
<style>
span { color: red;}
.td {font-weight: bold;}
</style>
</head>
<body style="background-color: blue; " >	 
<center>
		<div style="background-image: url('<%=request.getContextPath()%>/img/1593169_230733393187_2.jpg');width:1024px; height:750px;">	
			<form action="<%=request.getContextPath()%>/UserController/doLogin" method="post">
			<table style="position:relative;top:270px; border: black 0px solid;">
				<tr>
					<td style="font-weight: bold;">账号：</td>
					<td><input type="text" name="username" id="username"  ></td>
					<td style="width: 115px;"><span id="usernamemsg"></span></td>
				</tr>
				<tr>
					<td class="td">密码：</td>
					<td><input type="password" name="password" id="password"></td>
					<td style="width: 115px;"><span id="passwordmsg"></span></td>
				</tr>
				<tr>
					<td class="td">验证码：</td>
					<td><input type="text" name="code" id="code" onmouseleave = "check()"/></td>
					<td><img src="<%=request.getContextPath()%>/ImgController" onclick="refresh(this)"/></td>
					<td style="width: 115px;"><span id="codemsg"></span></td>
				</tr>
				<tr>
					<td class="td"><input type="checkbox" name="dayslogin" value="qqq">七天免登陆</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="dologin" value="登录" disabled></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><span id="loginmsg">${msg}</span></td>
					<td></td>
				</tr>
			</table>
			</form>
	</div>
</center>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(function () {
	if(<%=check%>){
		window.location.href="<%=request.getContextPath()%>/UserController/easyLogin"
	}	
})
function check() {
		
	if($("#username").val() == ""){
		$("#usernamemsg").html("账号不能为空");
	}
	else if($("#password").val() == ""){
		$("#passwordmsg").html("密码不能为空");
	}
	else if($("#code").val() == ""){
		$("#codemsg").text("验证码不能为空");
	}
	else {
		$("#dologin").prop("disabled",false);
	}
	$("#username").attr("onmousedown","cleanAll()")
	$("#password").attr("onmousedown","cleanAll()")
	$("#code").attr("onmousedown","cleanAll()")
	
}
function cleanAll() {
	
			$("#usernamemsg").text("");
			$("#passwordmsg").text("");
			$("#codemsg").text("");
			$("#loginmsg").text("");		
			$("#dologin").prop("disabled",true);
}	


function refresh(element) {
		//加入随机数的目的为了避免因为缓存机制的原因，导致请求路径相同而页面不刷新的问题
		element.src = "<%=request.getContextPath()%>/ImgController?r=" + Math.random();
	
	}
</script>
</html>