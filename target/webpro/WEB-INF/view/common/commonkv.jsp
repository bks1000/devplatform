<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/8/21
  Time: 14:52
  通用key - value界面：通过列表选择，返回{id，text}对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../base/jscss.jsp"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#dg').datagrid({
                //url:''
                data:${data}
            });
        });
        function getSelectRow() {
            var row = $('#dg').datagrid('getSelected');
            if(row==null){
                $.messager.alert('警告','请选择！');
                return null;
            }
            return row;
        }
        function ret() {
            var row = getSelectRow();
            if(row){
                var obj = {};
                obj.id=row.id;
                obj.name=row.name;
                obj.text = row.text;
                //this.parent.$("#middleman").val(obj);
                this.parent.middleman=obj;
                this.parent.OP.closeDialog2();
            }
        }
        function close() {
            this.parent.OP.closeDialog2();
        }
    </script>
</head>
<body>
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%"
           data-options="singleSelect:true,fit:false,fitColumns:true,striped:true,toolbar:tb">
        <thead>
        <tr>
            <th data-options="field:'id',width:0,align:'center',hidden:true">id</th>
            <th data-options="field:'text',width:240,align:'center',fixed:true">描述</th>
            <th data-options="field:'name',width:240,align:'center',fixed:true">别名</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="ret()">确定</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="close()">关闭</a>
    </div>
</body>
</html>
