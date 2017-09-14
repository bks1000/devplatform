<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
    	var dto = ${dto};
    	$(document).ready(function () {
            $("#ff").validate();
            $('#btnsave').bind('click', function(){
                //TODO:表单验证
                /*if($("#ff").validate()){
                    console.log($("#ff").validate());
                    return;
                }*/
                //console.log($('#ff').form('getData',true));
                var param = $('#ff').form('getData',true);
                $.post('${ctx}/roles/save',param,function (data) {
                    parent.OP.closeDialog();
                })
            });
            $('#btncancel').bind('click', function(){
                parent.OP.closeDialog();
            });

            if (dto!=null && dto!=undefined && dto!=0){
                $('#ff').form('setData',dto);
            }
        });
    </script>
</head>
<body>
    <form id="ff" class="container" role="form">
		<input type="hidden" id="rid" name="rid">
		<div class="row">
            <label class="col-xs-2 control-label" for="rname">角色名称:</label>
            <div class="col-xs-10">
                <input class="form-control" id="rname" name="rname" maxlength="50" type="text" placeholder="请输入rname" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="description">角色描述:</label>
            <div class="col-xs-10">
                <input class="form-control" id="description" name="description" maxlength="20" type="text" placeholder="请输入description" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="isadmin">是否管理员:</label>
            <div class="col-xs-1">
                <input class="form-control" type="checkbox" id="isadmin" name="isadmin" value="1" checked="checked" required>
            </div>
        </div>
		<div class="row">
            <span class="col-xs-8"></span>
            <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
            <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>
    </form>
</body>
</html>