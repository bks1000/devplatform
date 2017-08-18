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
	        window.location.href='${ctx}/forminfo/list';
	    }
	    function openWin() {
	        parent.OP.showDialog({
	            title:'新增实体',
	            href:'${ctx}/forminfo/add',
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
	    function updateWin() {
	        var row = getSelectRow();
	        if(row){
                parent.OP.showDialog({
                    title:'修改forminfo',
                    href:'${ctx}/forminfo/update?id='+row.fid,
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
	    function del() {
	        var row = getSelectRow();
	        if (row){
                $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                    if (r){
                        var id =row.fid;
                        $.get("${ctx}/forminfo/del",{"id":id},function (data) {
                            refresh();
                        });
                    }
                });
            }
	    }
	    function create() {
            var row = getSelectRow();
            if(row){
                var id =row.fid;
                $.get("${ctx}/forminfo/build",{"id":id},function (data) {
                    refresh();
                });
            }

        }
        function enable() {
            $.messager.alert('警告','暂不实现');
        }
        function unenable() {
            $.messager.alert('警告','暂不实现');
        }
	    /*datagrid 格式化显示*/
        function setrs(value, row, index) {
            if (row.rs){
                return "启用";
            } else {
                return "停用";
            }
        }
        function setcreate(value,row,index) {
            return row.iscreate==1? "已生成":"未生成";
        }
        function settbname(value, row, index) {
            return "c_"+row.fname;
        }
    </script>
</head>
<body>
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%"
           data-options="singleSelect:true,fit:false,fitColumns:true,striped:true,toolbar:tb">
        <thead>
        <tr>
			<th data-options="field:'fid',width:40,align:'center'">Fid</th>
			<th data-options="field:'tid',width:40,align:'center'">Tid</th>
			<th data-options="field:'fname',width:40,align:'center'">实体名称</th>
			<th data-options="field:'fmark',width:40,align:'center'">实体描述</th>
            <th data-options="field:'rs',width:40,align:'center'" formatter="setrs">状态</th>
            <th data-options="field:'iscreate',width:40,align:'center'" formatter="setcreate">是否生成表</th>
            <th data-options="field:'tablename',width:40,align:'center'" formatter="settbname">表名</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openWin()">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateWin()">修改</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="enable()">停用</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="unenable()">启用</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="create()">生成实体</a>
    </div>
</body>
</html>