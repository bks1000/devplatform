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

                $.post('${ctx}/organization/save',param,function (data) {
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
		<input type="hidden" id="orgid" name="orgid">
		<div class="row">
            <label class="col-xs-2 control-label" for="orgname">机构名称:</label>
            <div class="col-xs-10">
                <input class="form-control" id="orgname" name="orgname" maxlength="100" type="text" placeholder="请输入机构名称" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="rs">是否启用:</label>
            <div class="col-xs-1">
                <input class="form-control" type="checkbox" id="rs" name="rs" value="1" checked="checked" required>
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="porgid">上级机构:</label>
            <div class="col-xs-10">
                <input id="porgid" class="easyui-combotree" name="porgid"
                       data-options="valueField:'id',textField:'text',url:'${ctx}/organization/tree'" />
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