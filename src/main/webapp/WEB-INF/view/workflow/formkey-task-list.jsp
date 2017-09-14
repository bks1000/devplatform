<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
	<title>待办任务列表</title>
	<jsp:include page="../base/jscss.jsp"/>
	<jsp:include page="../base/global.jsp"/><!--这里的taglib配置不起作用，要放到上面-->
    <link href="${ctx }/static/common/plugins/jui/extends/timepicker/jquery-ui-timepicker-addon.css" type="text/css" rel="stylesheet" />
    <link href="${ctx }/static/common/plugins/qtip/jquery.qtip.min.css" type="text/css" rel="stylesheet" />

    <script src="${ctx }/static/common/plugins/jui/jquery-ui-${themeVersion }.min.js" type="text/javascript"></script>
    <script src="${ctx }/static/common/plugins/jui/extends/timepicker/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/plugins/jui/extends/i18n/jquery-ui-date_time-picker-zh-CN.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/plugins/validate/jquery.validate.pack.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/plugins/qtip/jquery.qtip.pack.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/plugins/html/jquery.outerhtml.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/plugins/blockui/jquery.blockUI.js" type="text/javascript"></script>
	<script src="${ctx }/static/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/static/module/activiti/workflow.js" type="text/javascript"></script>
	<script src="${ctx }/static/module/form/formkey/formkey-form-handler.js" type="text/javascript"></script>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">${message}</div>
		<!-- 自动隐藏提示信息 -->
		<script type="text/javascript">
		setTimeout(function() {
			$('#message').hide('slow');
		}, 5000);
		</script>
	</c:if>
	<table width="100%" class="table table-bordered table-hover">
		<tr>
			<th>任务ID</th>
			<th>任务Key</th>
			<th>任务名称</th>
			<th>流程定义ID</th>
			<th>流程实例ID</th>
			<th>优先级</th>
			<th>任务创建日期</th>
			<th>任务逾期日</th>
			<th>任务描述</th>
			<th>任务所属人</th>
			<th>操作</th>
		</tr>

		<c:forEach items="${page.result }" var="task">
		<tr>
			<td>${task.id }</td>
			<td>${task.taskDefinitionKey }</td>
			<td>${task.name }</td>
			<td>${task.processDefinitionId }</td>
			<td>${task.processInstanceId }</td>
			<td>${task.priority }</td>
			<td>${task.createTime }</td>
			<td>${task.dueDate }</td>
			<td>${task.description }</td>
			<td>${task.owner }</td>
			<td>
				<c:if test="${empty task.assignee }">
					<a class="claim" href="${ctx }/form/formkey/task/claim/${task.id}">签收</a>
				</c:if>
				<c:if test="${not empty task.assignee }">
					<%-- 此处用tkey记录当前节点的名称 --%>
					<a class="handle" tkey='${task.taskDefinitionKey }' tname='${task.name }' tid='${task.id }' href="#">办理</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<tags:pagination page="${page}" paginationSize="${page.pageSize}"/>
	<!-- 办理任务对话框 -->
	<div id="handleTemplate" class="template"></div>

</body>
</html>
