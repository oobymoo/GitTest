<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<center>
<body>
	<div >
	请输入用户名:
	<input type="text" id="username" onblur="checkname()"/>
	</div>
	<div>
	请输入密码：  
	<input type="text"id="userpassword"/>
	</div>
	<span id="rename"></span>
</body>
</center>
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript">
function checkname() {
	$.post(
			"UserServlet",
			{name:$("#username").val()},
			function (data) {
				
					$("#rename").text(data);
				
				},
			
				"text"
	);
}
</script>
</html>