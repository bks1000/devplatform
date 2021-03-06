<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../base/jscss.jsp"/>
	<jsp:include page="../base/global.jsp"/><!--这里的taglib配置不起作用，要放到上面-->
	<script>
		/*var notLogon = ${empty user};
		if (notLogon) {
			location.href = '${ctx}/login?error=nologon';
		}*/
	</script>
	<title>流程列表</title>

	<script src="${ctx }/static/jui/jquery-ui.min.js" type="text/javascript"></script>
	<link href="${ctx }/static/jui/jquery-ui.min.css" type="text/css">
    <script type="text/javascript">
    $(function() {
        // 初始化对话框
        $("#createModelTemplate").dialog();
        $("#createModelTemplate").dialog("close");//关闭对话框
    	$('#create').button({
    		icons: {
    			primary: 'ui-icon-plus'
    		}
    	}).click(function() {
    		$('#createModelTemplate').dialog({
    			modal: true,
    			width: 500,
    			buttons: [{
    				text: '创建',
    				click: function() {
    					if (!$('#name').val()) {
    						alert('请填写名称！');
    						$('#name').focus();
    						return;
    					}
                        setTimeout(function() {
                            location.reload();
                        }, 1000);
    					$('#modelForm').submit();
    				}
    			}]
    		});
    	});
    });

		function showSvgTip() {
			alert('点击"编辑"链接,在打开的页面中打开控制台执行\njQuery(".ORYX_Editor *").filter("svg")\n即可看到svg标签的内容.');
		}
    </script>
</head>
<body style="width:80%">
	<c:if test="${not empty message}">
	<div class="ui-widget">
			<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
				<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				<strong>提示：</strong>${message}</p>
			</div>
		</div>
	</c:if>
	<div style="text-align: right"><button id="create">创建</button></div>
	<table width="100%" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>KEY</th>
				<th>Name</th>
				<th>Version</th>
				<th>创建时间</th>
				<th>最后更新时间</th>
				<th>元数据</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="model">
				<tr>
					<td>${model.id }</td>
					<td>${model.key }</td>
					<td>${model.name}</td>
					<td>${model.version}</td>
					<td>${model.createTime}</td>
					<td>${model.lastUpdateTime}</td>
					<td>${model.metaInfo}</td>
					<td>
						<a href="${ctx}/modeler.html?modelId=${model.id}" target="_blank">编辑</a>
						<a href="${ctx}/workflow/model/deploy/${model.id}">部署</a>
						导出(<a href="${ctx}/workflow/model/export/${model.id}/bpmn" target="_blank">BPMN</a>
						|&nbsp;<a href="${ctx}/workflow/model/export/${model.id}/json" target="_blank">JSON</a>
						|&nbsp;<a href="javascript:;" onclick='showSvgTip()'>SVG</a>)
                        <a href="${ctx}/workflow/model/delete/${model.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="createModelTemplate" title="创建模型" class="template">
        <form id="modelForm" action="${ctx}/workflow/model/create" target="_blank" method="post">
		<table>
			<tr>
				<td>名称：</td>
				<td>
					<input id="name" name="name" type="text" />
				</td>
			</tr>
			<tr>
				<td>KEY：</td>
				<td>
					<input id="key" name="key" type="text" />
				</td>
			</tr>
			<tr>
				<td>描述：</td>
				<td>
					<textarea id="description" name="description" style="width:300px;height: 50px;"></textarea>
				</td>
			</tr>
		</table>
        </form>
	</div>
</body>
</html>