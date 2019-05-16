<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户授权——所有用户展示</title>
</head>
<style>
table {
	text-align: center;
}

th{	text-align: center;}

</style>
<body>
<h1>所有用户信息：</h1>
<a style="float: left;" href="<%=request.getContextPath()%>/PermissionController/backIndex">跳转主页</a>
<center>

<table  id=massage>
	<tr>
		<th>
			<input type="checkbox" id="checkall" onclick="checkall()"/>全选
			<input type="button" onclick="deleteMany()" value="批量删除"/>
		</th>
		<th>id</th>
		<th>用户名</th>
		<th>部门</th>
		<th>注册时间</th>
		<th>操作</th>
	</tr>
</table>
</center>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(
	showAllUser()
)
	function showAllUser() {
		
	$.ajax({
		url:"<%=request.getContextPath()%>/PermissionController/showAllUser",
		type:"get",
		dataType:"json",
		success : function (data) {
			
			$("#massage tr:gt(0)").remove();
			var tr="";
			$.each(data,function(index,obj) {
				tr="<tr>"+
						"<td>"+
							"<input type='checkbox' class='check' value="+obj.id+"/>"+
						"</td>"+
						"<td>"+obj.id+"</td>"+
						"<td>"+obj.username+"</td>"+
						"<td>"+obj.usercname+"</td>"+
						"<td>"+obj.addTime+"</td>"+
						"<td>"+
							"&nbsp;<input type='button' value='删除' onclick='deleteMany("+obj.id+")'/>&nbsp;"+
							"<input type='button' value='授权'/>"+
						"</td>"+
					"</tr>"
				$("#massage").append(tr);
			})
			
		},
		error:function(data){
			alert("后台报错");
		},
		
	})
}

function deleteMany(id) {
	var result = confirm("是否确认删除");
	if(result){
		var arr = [];
		$.each($(".check:checked"),function(index,obj){
			arr.push(obj.value);
			alert(arr);
		})
		
		
		
	}
	
}
	function checkall() {
		
	if($("#checkall").prop("checked")==true){
		$(".check").prop("checked",true);
	}
}

</script>
</html>