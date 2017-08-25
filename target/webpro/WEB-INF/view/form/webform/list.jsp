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
	        window.location.href='${ctx}/webform/list';
	    }
	    function openWin() {
	        parent.OP.showDialog({
	            title:'新增webform',
	            href:'${ctx}/webform/add',
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
		            title:'修改webform',
		            href:'${ctx}/webform/update?id='+row.id,
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
	        	var id =row.id;
		        $.get("${ctx}/webform/del",{"id":id},function (data) {
		            refresh();
		        });
	        }
	    }
	    function design() {
            var row = getSelectRow();
            if(row){
                var id =row.wid;
                parent.OP.showDialog({
                    title:'设计表单',
                    href:'${ctx}/webform/fdesign/'+id,
                    width:settings.dialogWidth,
                    height:settings.dialogHeith,
                    maximized:true,
                    onClose:function () {
                        //刷新
                        refresh();
                        //TODO:无刷新加载
                    }
                });
            }
        }
	    /*datagrid 格式化显示*/
	    //<th data-options="field:'rs',width:40,align:'center'" formatter="setrs">状态</th>
        function setrs(value, row, index) {
            if (row.rs){
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
			<th data-options="field:'wid',width:40,align:'center',hidden:true">wid</th>
			<th data-options="field:'tid',width:40,align:'center',hidden:true">tid</th>
			<th data-options="field:'title',width:340,align:'center',fixed:true">名称</th>
			<th data-options="field:'formkey',width:340,align:'center',fixed:true">别名</th>
			<th data-options="field:'boid',width:40,align:'center',hidden:true">boid</th>

            <th data-options="field:'tname',width:80,align:'center',fixed:true">分类</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openWin()">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateWin()">修改</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="design()">设计表单</a>
    </div>
</body>
</html>