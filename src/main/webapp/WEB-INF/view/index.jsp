<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乌托邦首页</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
</head>
<body>
	
<center>
<div class="container-fluid">
	<div class="row-fluid">
		<div style="background: #00ffff;padding: 10px;" class="span12">
		<h1>欢迎登陆乌托邦管理系统</h1><br/>
		
		<span style="position: absolute;left:80%;top: 8.5%;">欢迎用户：${findUserByName.username}登录&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/UserController/newUser">切换用户</a></span>
		</div>
	</div>
	
	<div class="row-fluid">
		<div style="float:left;width:15%; background:#ff3eff;" class="span3">
			<jsp:include page="permission_index.jsp"></jsp:include>
		</div>
		<div style="float:right; background: orange; width: 85%;" >
			<c:if test="${url eq null}">
				<jsp:include page="show_index.jsp"></jsp:include>
			</c:if>
			<c:if test="${url ne null}">
				<jsp:include page="${url}"></jsp:include>
			</c:if>
		</div>
	</div>
</div>
</center>
</body>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
</html>