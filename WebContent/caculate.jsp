<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="num1"/><!--JQ选择器使用id传值，而不是用name  -->
	<select id="sc">
		<option>+</option>
		<option>-</option>
		<option>*</option>
		<option>/</option>
	</select>
	<input type="text" id="num2"/>
	<br/>
	<input type="button" onclick="getcount()" value="计算" /><!-- 通过按钮触发事件"getcount()" -->
	计算结果为：<span id="count"></span>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript">
function getcount() {   //被按钮触发的自建JQ方法"getcount()"
	$.post(
			"CountServlet",
			{value:$("#num1").val(),value1:$("#num2").val(),carry:$("#sc").val()},//依照创建AJAX对象的方法——{a:b}的格式创建请求参数
			function (data) //接受从servlet后台传到前台的数据
			{
				$("#count").text(data);//传值到id为"count"的span标签。$(#)——为JQ的选择器，#号后填id。后用"."来调用执行的功能，例如val,text
			},	
			"text"
		);
	
}

</script>
</html>