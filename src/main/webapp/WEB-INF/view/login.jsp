<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%//取出cookie，展示在用户名和密码的文本框内，下面的方法时取出客户端保留的所有cookie文件,因为一个cookie只是一个文件，用户名和密码是两个文件，所以创建一个数字在装值
 	Cookie[] cookies=request.getCookies();
 	//getname()取键的方法，getvalue()取值的方法。与map数组根据键取值不同
 	String check="";
 	String name="";
 	String password="";
 	boolean easyLogin = false;
 	if(cookies!=null&&cookies.length>0){
 		for(Cookie cookie:cookies){
 			if("cloudNameCookie".equals(cookie.getName())){
 				name=cookie.getValue(); 				
 			//如果取到的cookie的名字与后台定义的cookie文件名相同，则取出
 				check= "checked";
 			//创建变量check的目的是为了在勾选后，下次登录时，复选框是选中的状态，标注出"checked"的状态，赋值给复选框的checked的属性
 			}
 			if("cloudPasswordCookie".equals(cookie.getName())){
 				password=cookie.getValue();
 				easyLogin = true;
 			//如果取到的cookie的名字与后台定义的cookie文件名相同，则取出
 			//此时只要点记住我，即执行七天免登陆方法
 			}
 		}
 	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云平台登录页</title>
<style>
 p {color: red;}
 span {	color: red;}
</style>
</head>
<center><body>
项目云平台
<hr>
<form action="<%=request.getContextPath()%>/CloudController/doLogin" method="post">
<table>
	<tr>
		<td>账号</td>
		<td><input type="text" name="userName"  id="username" value="<%=name%>"/></td>
		<td><span id="namemessage"></span></td>
	</tr>
	<tr>
		<td>密码</td>
		<td><input type="text" name="password" id="password" value="<%=password%>"/></td>
		<td><span id="passmessage"></span></td>
	</tr>
	<tr>
	<td>验证码</td>
	<td>
		<input type="text" name="code"/>
	</td>
	<td>
		<img src="<%=request.getContextPath()%>/ImgController" onclick="refresh(this)"/><!-- 此处产生图片形式验证码，如果需要点图片更换验证码，后面还需要把script的文件一起复制过来 -->
	</td>
	</tr>
	 <tr>
	  	<td></td>
	  	<td><input type="checkbox" name="remberme" value="kkk" <%=check%>/>记住我</td>
	  	<td><input type="checkbox" name="easy" value="oooo">七天免登陆</td>
  	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="登录" /></td>
		<td><a href="CloudServlet?flag=register" >立即注册</a></td>
	</tr>
</table>
<p>${msg}</p>
</form>
</body>
</center>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(function () {
	if(<%=easyLogin%>){
		alert("easyLogin");
		window.location.href="<%=request.getContextPath()%>/CloudController/easyLogin"
	}
})
	
	
	function refresh(element) {
		//加入随机数的目的为了避免因为缓存机制的原因，导致请求路径相同而页面不刷新的问题
		element.src = "<%=request.getContextPath()%>/ImgController?r=" + Math.random();
		//*********************跳转controller时并没有返回到根目录就可以跳转，这个是为什么？*************************
	}
	
	
	$("#password").attr("onmouseout","check()");
	function check() {
		if($("#username").val() == ""){
			$("#namemessage").html("账户不能为空！");
		}
		if($("#password").val() == ""){
			$("#passmessage").html("密码不能为空！");
		}
	}
	$("#username").attr("onmousedown","clean()");
	function clean() {
			$("#namemessage").html("");
			$("#passmessage").html("");
	}
	
</script>
</html>