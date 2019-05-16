<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乌托邦主页</title>
</head>
<body>
	
	<c:forEach items="${majorPermissionList}" var="major">
		<h3>${major.permissionName}</h3><br/>
		<c:forEach items="${major.minorPermissinoList}" var="minor">
			<c:if test="${minor.permissionUrl ne null}">
				&nbsp;&nbsp;<a href="<%=request.getContextPath()%>${minor.permissionUrl}">${minor.permissionName}</a><br/>
			</c:if>
			<c:if test="${minor.permissionUrl eq null}">
				&nbsp;&nbsp;<a href="#">${minor.permissionName}</a><br/>
			</c:if>
			
		</c:forEach>
	</c:forEach>
</body>
</html>