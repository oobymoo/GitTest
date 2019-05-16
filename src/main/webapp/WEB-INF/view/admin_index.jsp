<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员首页</title>
</head>
<body>
<center>
北方图书管理系统
<hr/>
<div style="float: right;">欢迎管理员：${findUserByName.id}登录</div><br/>
<!--<div style="clear: both;"></div> 此处与<br/>实现功能是一样 -->

编号:<input type="text" id="no"/><br/>

书名:<input type="text" id="name"/><br/>

<input type="button" value="搜索"  onclick="findPage(1)"/>

<input type="button" id="deletemany"value="批量删除" />
<table id="message">
	<tr>
		<td><input type="checkbox" id="checkall"/></td>
		<td>编号</td>
		<td>书名</td>
		<td>作者</td>
		<td>出版时间</td>
		<td>价格</td>
		<td>数量</td>
		<td>操作</td>
	</tr>
</table>

<span id="pagemessage"></span>
<a href="#" id="lastpage"></a>
<span id="jumppage"></span>
<a href="#" id="nextpage"></a>

</center>
</body>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(function () { 
	findPage(1) 
});
		function findPage(pagecount) {
			$.ajax({
				url:"<%=request.getContextPath()%>/AdminController/doSearch",
				type:"post",
				data:{no : $("#no").val(),name : $("#name").val(),countPage : pagecount},
				dataType:"json",
				success : function(data) {
					$("#deletemany").attr("onclick","deleteMany("+data.currentPage+")");
					
					var td = "<td><a href='#'>购买</a></td>"
					if("${findUserByName.level}" == 0){
						//js中可以使用el表达式接值，但是需要用双引号把el表达式装在里面
						td="<td><a href='#'>删除</a></td>"
					}
					
					$("#message tr:gt(0)").remove();
					//！！！重点！！！选择器tr:gt中间不能有空格出现，必须紧紧接在一起
					$.each(data.bookPageList,function (index,obj) {
						var tr=$("<tr>"+
									"<td><input type='checkbox' class='check' value='"+obj.no+"'</td>"+//第一次写，取值的时候把“</td>”也当值给取进去了，
									"<td>"+obj.no+"</td>"+                                //原因是拼接有问题！写成了value"+obj.no+"</td>"
									"<td>"+obj.name+"</td>"+
									"<td>"+obj.author+"</td>"+
									"<td>"+obj.publish+"</td>"+
									"<td>"+obj.price+"</td>"+
									"<td>"+obj.store+"</td>"+
									td+
								"</tr>"	
						)
						$("#message").append(tr);
						
						$("#pagemessage").html("共"+data.totalPage+"页，当前第"+data.currentPage+"页");
						if(data.currentPage!=1){
							$("#lastpage").html("上一页");
						}
						else{
							$("#lastpage").html("");
						}
						if(data.currentPage != data.totalPage){
							$("#nextpage").html("下一页");
						}
						else{
							$("#nextpage").html("");
						}
						$("#lastpage").attr("onclick","findPage("+(data.currentPage-1)+")");
						$("#nextpage").attr("onclick","findPage("+(data.currentPage+1)+")");
					});	
						$("#jumppage").empty();//必须用empty()的原因是用gt选择器，配合remove只能选到0，0也就是第一个a标签，这第一个a标签永远会在
					for(var i=1;i<=data.totalPage;i++){
						var a=$("<a href='#' onclick='findPage("+i+")'>"+i+"页</a>")
						$("#jumppage").append(a);
					}
					
				},
				error : function (data) {
					alert("后台报错");
				}
			})
}
		
		function deleteMany(countPage) {
			
			var result=confirm("是否确定删除?");
			if(result){
				
				alert("进入批量删方法");	
				
				var arr=[];
				$.each($(".check:checked"),function(index,obj){
					arr.push($(obj).val());//用这种js的取值方式为什么不好用，必须要换成jq的obj.value()
					alert(arr);
				});
				$.ajax({
					url:"<%=request.getContextPath()%>/AdminController/deleteBook",
					type:"post",
					data:{manyNo : arr.toString()},
					dataType:"text",
					success : function (data) {
						if(data == "true"){
							alert("成功删除");
							findPage(countPage);
						}
						else{
							alert("删除失败");
						}
					},
					error: function (data) {
						alert("后台报错");
					}
				})

				
			}
						
		}

</script>
</html>