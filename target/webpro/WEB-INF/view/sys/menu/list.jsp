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
            window.location.href='${ctx}/menu/list'
        }
        function openWin() {
            parent.OP.showDialog({
                title:'新增菜单',
                href:'${ctx}/menu/add',
                width:settings.dialogWidth,
                height:settings.dialogHeith,
                maximized:false,
                onClose:function () {
                    //刷新
                    //window.location.href='${ctx}/menu/list'
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
                title:'修改菜单',
                href:'${ctx}/menu/update?id='+row.id,
                width:settings.dialogWidth,
                height:settings.dialogHeith,
                maximized:false,
                onClose:function () {
                    //刷新
                    //window.location.href='${ctx}/menu/list'
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
            $.get("${ctx}/menu/del",{"id":id},function (data) {
                refresh();
            });
        }
    </script>
</head>
<body>
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%"
           data-options="singleSelect:true,fit:true,fitColumns:true,striped:true,toolbar:tb">
        <thead>
        <tr>
            <th data-options="field:'id',width:10,align:'center'">id</th>
            <th data-options="field:'name',width:20,align:'center'">菜单名</th>
            <th data-options="field:'parentid',width:10,align:'center'">上级编码</th>
            <th data-options="field:'idx',width:10,align:'center'">序号</th>
            <th data-options="field:'url',width:20,align:'center'">url</th>
            <th data-options="field:'rs',width:10,align:'center'">是否启用</th>
            <th data-options="field:'remark',width:20,align:'center'">备注</th>
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