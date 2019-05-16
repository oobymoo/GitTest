<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<center>	
	<h1>登录乌托邦管理系统</h1>
	<a href="https://www.baidu.com">百度一下</a>
	<br/>
	<a style="float:left; " href="<%=request.getContextPath()%>/PermissionController/backIndex">跳转主页</a>
	<form action="<%=request.getContextPath()%>/PermissionController/addImg" enctype="multipart/form-data" method="post">
	图片：<input type="file" name="picture" onchange="updataImg()">
	<br/>
	<input type="submit" value="上传图片" id="upimg" disabled style="background-color:#9955ff; ">
	</form>
	<c:forEach items="${findImgList}" var="findImg">
	<img src="../img/${findImg.picture}"><br/>
	</c:forEach>
	</center>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.js"></script>
<script type="text/javascript">
	function updataImg() {
		$("#upimg").prop("disabled",false);
		$("#upimg").css(style="background-color:red;");
	}

</script>
</html>