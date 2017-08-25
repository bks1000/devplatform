<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
    	/*初始化*/
	    $(document).ready(function () {
	        $('#dg').datagrid({
	            data:${data} //TODO:生成时，会导致错误，故添加下划线，使用时修改！
	        });
            $("#searchkey").textbox('button').bind("click", function () {
                var text = $("#searchkey").textbox('getValue');
                alert (text);
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
	        window.location.href='${ctx}/bodef/list';
	    }
	    function openWin() {
	        parent.OP.showDialog({
	            title:'新增BO定义',
	            href:'${ctx}/bodef/add',
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

	    function del() {
	        var row = getSelectRow();
	        if(row){
	        	var id =row.id;
		        $.get("${ctx}/bodef/del",{"id":id},function (data) {
		            refresh();
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
           data-options="singleSelect:true,fit:false,fitColumns:true,striped:true,toolbar:tb">
        <thead>
        <tr>
			<th data-options="field:'boid',width:0,align:'center',hidden:true">boid</th>
			<th data-options="field:'bomark',width:240,align:'center',fixed:true">描述</th>
			<th data-options="field:'boname',width:240,align:'center',fixed:true">别名</th>
			<th data-options="field:'fid',width:0,align:'center',hidden:true">fid</th>
			<th data-options="field:'tid',width:0,align:'center',hidden:true">tid</th>
			<th data-options="field:'ts',width:40,align:'center',hidden:true">ts</th>
            <th data-options="field:'rs',width:140,align:'center',fixed:true,formatter:setrs">状态</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openWin()">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
        <input id="searchkey" name="searchkey" class="easyui-textbox" data-options="buttonText:'搜索',buttonIcon:'icon-search',prompt:'别名/描述'" style="width:300px;height:24px;">
    </div>
</body>
</html>