#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="${package}.dto.BookType"%>
<%@include file="base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书类型列表</title>
<%@ include file="base/jscss.jsp"%>
<!-- jstl Foreach    http://blog.csdn.net/liu78778/article/details/3973786 -->
<script type="text/javascript">
	//只能选择一个
	function chk(obj) {
		var state = obj.checked;
		${symbol_dollar}(":checkbox").each(function(i) {
			this.checked = false;
		});
		obj.checked = state;
	}
	//获取选中行的主键ID
	function getCheckID() {
		var ret="";
		${symbol_dollar}(":checkbox").each(function() {
			if (this.checked) {
				var id = this.id;
				//alert(id);
				ret = id.slice(3);
				return false;//退出循环
			}
		});
		return ret;
	}

	function btnUpdate() {
		var id = getCheckID();
		if (id == undefined || id == "undefined" ||id=="") {
			alert("请选择要修改的数据！");
			return;
		}
		//alert(id);
		//window.location.href = "${symbol_dollar}{ctx}/type/update.do?id=" + id;
		${symbol_dollar}('${symbol_pound}myModal').modal({
		  keyboard: false,
		  remote:"${symbol_dollar}{ctx}/type/update.do?id=" + id
		});
	}

	function btnAdd() {
		window.location.href = "${symbol_dollar}{ctx}/type/addbooktype.do";
	}
	
	function query() {
		location.reload(true);
	}
	
	function btnDel() {
		var id = getCheckID();
		if (id == undefined || id == "undefined" ||id=="") {
			alert("请选择要修改的数据！");
			return;
		}
		${symbol_dollar}.post("${symbol_dollar}{ctx}/type/del.do",{id:id},function(data){
			if(data.code==0){
				alert("删除成功！");
				query();
			}
		});
	}
</script>
</head>
<body>
	
	
	<div class="btn-group">
		<button type="button" class="btn btn-default" onclick="query()">查询</button>
		<button class="btn btn-default" data-toggle="modal"
			data-target="${symbol_pound}myModal" href="${symbol_dollar}{ctx}/type/addbooktype.do">新增</button>
		<button id="btnUpdate" type="button" class="btn btn-default"
			onclick="btnUpdate()">修改</button>
		<button type="button" class="btn btn-default" onclick="btnDel()">删除</button>
	</div>
	<table class="table table-hover">
		<thead class="thead-inverse">
			<tr scope="row">
				<th>选择</th>
				<th>行号</th>
				<th>ID</th>
				<th>类型名称</th>
				<th>PID</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${symbol_dollar}{types }" varStatus="status">
				<tr scope="row">
					<td><input id="txt${symbol_dollar}{item.id}" type="checkbox"
						onclick="chk(this)" /></td>
					<td>${symbol_dollar}{status.index+1 }</td>
					<td>${symbol_dollar}{item.id }</td>
					<td>${symbol_dollar}{item.typeName }</td>
					<td>${symbol_dollar}{item.parentId }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
				
	<!-- 按钮触发模态框 -->
	<%-- <button class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="${symbol_pound}myModal" href="${symbol_dollar}{ctx}/type/addbooktype.do">开始演示模态框</button> 
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
				</div>
				<div class="modal-body">在这里添加一些文本</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>--%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>