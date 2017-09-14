<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
    	/*初始化*/
	    $(document).ready(function () {
	        $('#dg').datagrid({
	            data:${data}
	        });
	    });
	    /*功能*/
        function getSelectRow() {
            var row = $('#dg').datagrid('getSelected');
            if(row==null){
                $.messager.alert('警告','请选择要操作的行！');
                return null;
            }
            return row;
        }
	    function refresh() {
	        window.location.href='${ctx}/organization/list';
	    }
	    function openWin() {
	        parent.OP.showDialog({
	            title:'新增机构',
	            href:'${ctx}/organization/add',
	            width:settings.dialogWidth,
	            height:settings.dialogHeith,
	            maximized:false,
	            onClose:function () {
	                //刷新
	                refresh();
	                //TODO:无刷新加载
	            }
	        });
	    }
	    function updateWin() {
	        var row = getSelectRow();
	        if(row){
	        	parent.OP.showDialog({
		            title:'修改机构',
		            href:'${ctx}/organization/update/'+row.orgid,
		            width:settings.dialogWidth,
		            height:settings.dialogHeith,
		            maximized:false,
		            onClose:function () {
		                //刷新
		                refresh();
		                //TODO:无刷新加载
		            }
		        });
	        }
	    }
	    function del() {
	        var row = getSelectRow();
	        if(row){
	        	var id =row.orgid;
		        $.get("${ctx}/organization/del/"+id,function (data) {
		            refresh();
		        });
	        }
	    }
	    /*datagrid 格式化显示*/
	    //<th data-options="field:'rs',width:40,align:'center'" formatter="setrs">状态</th>
        function setrs(value, row, index) {
            if (row.rs=="1"){
                return "启用";
            } else {
                return "停用";
            }
        }
    </script>
</head>
<body>
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%"
           data-options="singleSelect:true,fit:true,fitColumns:true,striped:true,toolbar:tb">
        <thead>
        <tr>
			<th data-options="field:'orgid',width:40,align:'center',hidden:true">orgid</th>
			<th data-options="field:'orgname',width:40,align:'center'">机构名称</th>
			<th data-options="field:'rs',width:40,align:'center',formatter:setrs">是否启用</th>
			<th data-options="field:'porgid',width:40,align:'center',hidden:true">上级机构</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openWin()">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateWin()">修改</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
    </div>
</body>
</html>