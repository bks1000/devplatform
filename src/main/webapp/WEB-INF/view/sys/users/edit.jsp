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
                $.post('${ctx}/users/save',param,function (data) {
                    parent.OP.closeDialog();
                })
            });
            $('#btncancel').bind('click', function(){
                parent.OP.closeDialog();
            });

            if (dto!=null && dto!=undefined && dto!=0){
                $('#ff').form('setData',dto,"ff");
            }
        });
    </script>
</head>
<body>
    <form id="ff" class="container" role="form">
		<input type="hidden" id="uid" name="uid">
        <input type="hidden" id="orgid" name="orgid" value="${orgid}">
		<div class="row">
            <label class="col-xs-2 control-label" for="uname">用户名:</label>
            <div class="col-xs-10">
                <input class="form-control" id="uname" name="uname" maxlength="50" type="text" placeholder="请输入用户名" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="nickname">昵称:</label>
            <div class="col-xs-10">
                <input class="form-control" id="nickname" name="nickname" maxlength="50" type="text" placeholder="请输入昵称" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="pwd">密码:</label>
            <div class="col-xs-10">
                <input class="form-control" id="pwd" name="pwd" maxlength="50" type="password" placeholder="请输入密码" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="email">邮箱:</label>
            <div class="col-xs-10">
                <input class="form-control" id="email" name="email" maxlength="50" type="text" placeholder="请输入邮箱" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="mobile">手机:</label>
            <div class="col-xs-10">
                <input class="form-control" id="mobile" name="mobile" maxlength="20" type="text" placeholder="请输入手机号" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="rs">是否启用:</label>
            <div class="col-xs-1">
                <input class="form-control" type="checkbox" id="rs" name="rs" value="1" checked="checked" required>
            </div>
        </div>
		<!--<div class="row">
            <label class="col-xs-2 control-label" for="orgid">所属机构:</label>
            <div class="col-xs-10">
                <input class="form-control" id="orgid" name="orgid" maxlength="10" type="text" placeholder="请输入机构id" required />
            </div>
        </div>-->
		<div class="row">
            <span class="col-xs-8"></span>
            <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
            <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>
    </form>
</body>
</html>