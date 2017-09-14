<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
    	/*初始化*/
	    $(document).ready(function () {
            $('#tt').tree({
                url:'${ctx}/menu/tree',
                checkbox:true,
                cascadeCheck:true,
                lines:true,
                loadFilter: function(data){
                    return data;
                }
            });
            //选择角色，绑定已经设置的权限。
            $('#dg').datagrid({
                onClickRow:function( rowIndex, rowData){
                    var rid =rowData.rid;
                    $.get("${ctx}/rolemenu/getmenus/"+rid,function (data) {
                       //console.log(data);
                        //alert(data instanceof Array);
                        data = eval('('+data+')');
                        //alert(data instanceof Array);
                        $(data).each(function (index, value){
                            var node = $('#tt').tree('find',value.mid);
                            $('#tt').tree('check',node.target);
                        });
                    });
                }
            });
	    });

        function setisadmin(value, row, index) {
            if (row.isadmin=="1"){
                return "管理员";
            } else {
                return "非管理员";
            }
        }
	    
	    function save() {
            var nodes = $('#tt').tree('getChecked');
            console.log(nodes);
            var row = $('#dg').datagrid('getSelected');
            if (!row){
                $.messager.alert('警告','请选择一个角色!');
                return;
            }
            if(nodes.length==0){
                $.messager.alert('警告','请选择要分配的菜单!');
                return;
            }
            //TODO 保存
            var mids = new Array();
            for (var i=0;i<nodes.length;i++){
                mids.push(nodes[i].id);
            }
            /*$.post("${ctx}/rolemenu/save",{"rid":row.rid,"mids":mids},function (data) {
                if(data.code==1){
                    $.messager.show("保存成功！");
                }
            });*/

            $.ajax({
                url: "${ctx}/rolemenu/save",
                type: "post",
                data: {
                    "rid": row.rid,
                    "mids": mids
                },
                traditional: true,//这里设置为true
                success: function(data) {
                    if(data.code==1){
                        $.messager.alert("提示","保存成功！","info");
                    }
                }
            });
        }

    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-2">
            <a class="easyui-linkbutton" iconCls="icon-save" plain="false" onclick="save()">保存</a>
        </div>
    </div>
    <div class="row" style="height:0px;padding-bottom:70%">
        <div class="col-xs-10" style="height:600px;">
            <table id="dg" class="easyui-datagrid" style="width:100%;height:100%"
                   data-options="singleSelect:true,fit:false,fitColumns:true,striped:true,url:'${ctx}/roles/json'">
                <thead>
                <tr>
                    <th data-options="field:'rid',width:40,align:'center',hidden:true">rid</th>
                    <th data-options="field:'rname',width:40,align:'center'">角色名称</th>
                    <th data-options="field:'description',width:40,align:'center'">描述</th>
                    <th data-options="field:'isadmin',width:40,align:'center',formatter:setisadmin">是否管理员</th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-xs-2" style="height:600px;">
            <ul id="tt"></ul>
        </div>
    </div>
</div>
</body>
</html>