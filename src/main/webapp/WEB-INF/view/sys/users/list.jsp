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

            $('#tt').tree({
                url:'${ctx}/organization/tree',
                loadFilter: function(data){
                    return data;
                }
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
	        window.location.href='${ctx}/users/list';
	    }
	    function openWin() {
            var treenode = $('#tt').tree('getSelected');
            if (treenode==null){
                $.messager.alert('警告','请选择部门！');
                return;
            }
	        parent.OP.showDialog({
	            title:'新增用户',
	            href:'${ctx}/users/add/'+treenode.id,
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
		            title:'修改用户',
		            href:'${ctx}/users/update/'+row.uid,
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
	        	var id =row.uid;
		        $.get("${ctx}/users/del/"+id,function (data) {
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

        function saveUserRole() {
            var row = getSelectRow();
            var roles = $('#dgrole').datagrid('getChecked');
            console.log(roles);
            var rids = new Array();
            for(var i=0;i<roles.length;i++){
                rids.push(roles[i].rid);
            }
            $.ajax({
                url: "${ctx}/users/saveRole",
                type: "post",
                data: {
                    "uid": row.uid,
                    "rids": rids
                },
                traditional: true,//这里设置为true
                success: function(data) {
                    if(data.code==1){
                        $.messager.alert("提示","保存成功！","info");
                        $('#myModal').modal('hide');
                    }
                }
            });
        }
    </script>
    <style type="text/css">

    </style>
</head>
<body>
    <div class="container">
        <div class="row" style="height:0px;padding-bottom:70%">
            <div class="col-xs-2">
                <ul id="tt"></ul>
            </div>
            <div class="col-xs-10" style="height:600px;">
                <div id="tb">
                    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openWin()">新增</a>
                    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateWin()">修改</a>
                    <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
                    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-toggle="modal" data-target="#myModal">设置角色</a>
                </div>
                <table id="dg" class="easyui-datagrid" data-options="singleSelect:true,fit:true,fitColumns:true,striped:true,toolbar:tb">
                    <thead>
                    <tr>
                        <th data-options="field:'uid',width:40,align:'center',hidden:true">uid</th>
                        <th data-options="field:'uname',width:40,align:'center'">用户名</th>
                        <th data-options="field:'nickname',width:40,align:'center'">昵称</th>
                        <th data-options="field:'email',width:40,align:'center'">邮箱</th>
                        <th data-options="field:'mobile',width:40,align:'center'">手机号</th>
                        <th data-options="field:'rs',width:40,align:'center',formatter:setrs">是否启用</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        设置角色
                    </h4>
                </div>
                <div class="modal-body">
                    <table id="dgrole" class="easyui-datagrid" style="width:100%;height:200px;"
                           data-options="singleSelect:true,fit:false,fitColumns:true,striped:true,idField:'rid',url:'${ctx}/roles/json'">
                        <thead>
                        <tr>
                            <th data-options="field:'ck',checkbox:true"></th>
                            <th data-options="field:'rid',width:40,align:'center',hidden:true">rid</th>
                            <th data-options="field:'rname',width:40,align:'center'">角色名称</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveUserRole()">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
</html>