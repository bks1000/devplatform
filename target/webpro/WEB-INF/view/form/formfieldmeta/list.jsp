<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
	    $(document).ready(function () {
	        $('#dg').datagrid({
	            data:${data}
	        });
	    });
	    function refresh() {
	        window.location.href='${ctx}/formfieldmeta/list';
	    }
	    function openWin() {
	        parent.OP.showDialog({
	            title:'新增表单字段',
	            href:'${ctx}/formfieldmeta/add',
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
	        var row = $('#dg').datagrid('getSelected');
	        if(row==null){
	            $.messager.alert('警告','请选择要修改的行！');
	            return;
	        }
	        parent.OP.showDialog({
	            title:'修改表单字段',
	            href:'${ctx}/formfieldmeta/update?id='+row.id,
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
	    function del() {
	        var row = $('#dg').datagrid('getSelected');
	        if(row==null){
	            $.messager.alert('警告','请选择要删除的行！');
	            return;
	        }
	        var id =row.id;
	        $.get("${ctx}/formfieldmeta/del",{"id":id},function (data) {
	            refresh();
	        });
	    }
    </script>
</head>
<body>
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%"
           data-options="singleSelect:true,fit:false,fitColumns:true,striped:true,toolbar:tb">
        <thead>
        <tr>
			<th data-options="field:'Ffid',width:40,align:'center'">Ffid</th>
			<th data-options="field:'Fid',width:40,align:'center'">Fid</th>
			<th data-options="field:'Note',width:40,align:'center'">Note</th>
			<th data-options="field:'Ffname',width:40,align:'center'">Ffname</th>
			<th data-options="field:'Required',width:40,align:'center'">Required</th>
			<th data-options="field:'Datatype',width:40,align:'center'">Datatype</th>
			<th data-options="field:'Datalength',width:40,align:'center'">Datalength</th>
			<th data-options="field:'Defaultvalue',width:40,align:'center'">Defaultvalue</th>
			<th data-options="field:'Sn',width:40,align:'center'">Sn</th>
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