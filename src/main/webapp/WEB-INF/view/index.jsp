<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云平台首页</title>
<style>
td{text-align: center;};
a{color: red;}
</style>
</head>
<center>
<body>
项目云平台
<hr/>
</center>
<!--<div style="float: left;">  -->
项目名称
<input type="text" id="proname" />&nbsp;
<input type="button" onclick="findByPage(1)" id="adminfind" value="查询" />&nbsp;
<c:if test="${user.usertype eq 0}"><!--通过'c if'语句进行判断，根据条件是否成立，执行相应功能，这里是执行添加a href标签-->
<a href="CloudProjectServlet?flag=toadd">添加项目</a>
</c:if>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div style="float: right;"><a href="<%=request.getContextPath()%>/CloudController/newUser">切换用户</a>&nbsp;欢迎用户<span style="color: red;">${findUserByName.userName}</span>登录</div><!-- el表达式（￥{}）自动忽略null，相关调用不会出现空指针 -->

<table  id="massage">
		<tr>
			<td><input type="button" id="deletemany" value="批量删除" /></td>
		</tr>
		<tr>
			<td><input type="checkbox" id="checkall" onclick="checkall()"/></td>
			<td>No.</td>
			<td>项目名称</td>
			<td>周期</td>
			<td>负责人</td>
		<c:if test="${findUserByName.userType eq 0}"><!--通过'c if'语句进行判断，根据条件是否成立，执行相应功能，这里是执行添加<td></td>标签  -->
			<td>操作</td>
		</c:if>
	</tr>
</table>
<span id="pagemessage"></span>
	<a href="#" id="lastpage"></a>
	<span id="junppage"></span>
	<a href="#" id="nextpage"></a>
</body>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(function () {
	findByPage(1)
})
function findByPage(countpage) {
	
	$.ajax({
		url:"<%=request.getContextPath()%>/CloudController/adminSerach",
		type:"post",
		data:{countPage:countpage,projectname:$("#proname").val()},
		dataType:"json",
		success:function (data) {
			$("#deletemany").attr("onclick","deletemany("+data.currentPage+")");
			$("#massage tr:gt(1)").remove();//在下次执行遍历方法时，清除上次得到的结果
		
			$.each(data.pageList,function(Index,obj){
				//根据条件，当usertype=0的时候，创建节点td
				
				var td = "";
				if ("${findUserByName.userType !=0}") {
					td = "<td>" + 
							"<a href='CloudProjectServlet?flag=goUpdate&proid="+obj.proId+"'>修改</a>&nbsp;&nbsp;" + 
							"<a href='#' onclick='todelete(" + obj.proId +")'>删除</a>" + 
						"</td>"
				}
				var username="";//创建变量，准备承接username的值，放到后面创建的td标签中;
				$.each(data.userList,function(Index,user){//遍历包含user属性的集合
					if(user.userId==obj.header){
						username=user.userName;
						}
				})//此括号是第二个each的结束
				//用jq语句创建tr
				var tr = $("<tr>"+
							"<td>"+
								"<input type='checkbox' class='check' onclick='check()' value='"+obj.proId+"'/>"+
							"</td>"+                                          
							"<td>"+(Index+1)+"</td>"+
							"<td><a href='CloudProjectServlet?flag=showbug&proid="+obj.proId+"'>"+obj.proName+"</a></td>"+
							"<td>"+obj.startTime+"---"+obj.endTime+"</td>"+
							"<td>"+username+"</td>"+
							td+
						"</tr>"
						
						);
				$("#massage").append(tr);
			});
			//此括号是第一个each的结束 
			$("#pagemessage").html("共"+data.totalPage+"页，当前第"+data.currentPage+"页");//这里的currentpage是实体类中的属性名
			if(data.currentPage!=1){//这里的currentpage是实体类中的属性名
				$("#lastpage").html("上一页");
			}
			else{
				$("#lastpage").html("");
			}
			if(data.currentPage!=data.totalPage){
				$("#nextpage").html("下一页");
			}//这里的currentpage是实体类中的属性名
			else{
				$("#nextpage").html("");
			}
			$("#lastpage").attr("onclick","findByPage("+(data.currentPage-1)+")");//这里的currentpage是实体类中的属性名
			$("#nextpage").attr("onclick","findByPage("+(data.currentPage+1)+")");//这里的currentpage是实体类中的属性名
			
			$("#junppage").empty();
			for(var i=1;i<=data.totalPage;i++){//这里的totalpage是实体类中的属性名
				var a= $("<a href='#'onclick='findByPage("+i+")'>"+i+"</a><span>&nbsp;&nbsp;&nbsp;</span>");
				$("#junppage").append(a);
			}
			
		},//此括号是success的结尾
		error : function (data) {
			alert("后台报错！");
		}
	});
	
}

	function deletemany(countpage) {
		var result = confirm("是否确定删除？");
		if(result){
			var arr = [];
		$.each($(".check:checked"),function(index,obj){
			arr.push($(obj).val());
		});
			$.ajax({
				url:"CloudProjectServlet",
				type:"get",
				data:{flag:"deletemany",manyno:arr.toString()},
				dataType:"text",
				success : function (data) {
					alert(data);
					if(data){
						alert(data);
						alert("杯具了！都删没有了");
					}
					else{
						alert(data);
						alert("没删成");
					}
				},
				error :function(data){
					alert("后台报错");
				}
			});
		}
		
	}

	
	function check() {
		//因为列表前面的复选框是遍历产生的，也就是会产生好几个通名的复选框，因此不能用id来标记，后来调用时会出现多个通id的，产生冲突
		//根据id不重复的特点，因此改用class来标记，因为同名class可以被多次调用，因此不会影响功能实现
		if($(".check:checked").length==$(".check").length){
			$("#checkall").prop("checked",true);
		}
		else{
			$("#checkall").prop("checked",false);
		}
	}
	
	function checkall() {
		if($("#checkall").prop("checked")==true){
			$(".check").prop("checked",true);
			//因为列表前面的复选框是遍历产生的，也就是会产生好几个通名的复选框，因此不能用id来标记，后来调用时会出现多个通id的，产生冲突
			//根据id不重复的特点，因此改用class来标记，因为同名class可以被多次调用，因此不会影响功能实现
		}
		else{
			$(".check").prop("checked",false);	
		}
	}
	
	
	
	function todelete (proid) {
		$.ajax({
			url:"CloudProjectServlet",
			type:"get",
			data:{flag:"todelete",proid:proid},
			dataType:"text",
			success : function (data) {
			//如果从后来传过来的是boolean类型的值，js可以自动给值赋成boolean型	
				if(data){
					alert("删除成功");
					findByPage(1)
				}
				else{
					alert("删除失败");
					findByPage(1)
				}
			},
		});
	}




</script>
</html>