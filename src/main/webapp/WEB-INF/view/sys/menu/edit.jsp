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
                //TODO:
                /*if($("#ff").validate()){
                    console.log($("#ff").validate());
                    return;
                }*/
                console.log($('#ff').form('getData',true));
                var param = $('#ff').form('getData',true);
                $.post('${ctx}/menu/save',param,function (data) {
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
        <div class="row">
            <label class= "col-xs-2 control-label" for="id">ID:</label>
            <div class="col-xs-10">
                <input class="form-control" id="id" name="id" maxlength="40" type="text" placeholder="请输入ID" required />
            </div>
        </div>
        <div class="row">
            <label class="col-xs-2 control-label" for="name">菜单名:</label>
            <div class="col-xs-10">
                <input class="form-control" id="name" name="name" maxlength="50" type="text" placeholder="请输入菜单名" required />
            </div>
        </div>
        <div class="row">
            <label class="col-xs-2 control-label" for="parentid">父级菜单:</label>
            <div class="col-xs-10">
                <input class="form-control"  id="parentid" name="parentid" maxlength="40" type="text" required />
            </div>
        </div>
        <div class="row">
            <label class="col-xs-2 control-label" for="idx">顺序号:</label>
            <div class="col-xs-10">
            <input class="form-control" id="idx" name="idx" maxlength="40" type="text" required />
            </div></div>
        <div class="row">
            <label class="col-xs-2 control-label" for="url">URL:</label>
            <div class="col-xs-10">
                <input class="form-control" id="url" name="url" maxlength="40" type="text" required />
            </div>
        </div>
        <div class="row">
            <label class="col-xs-2 control-label" for="rs">是否启用:</label>
            <div class="col-xs-1">
                <input class="form-control" id="rs" name="rs" maxlength="40" type="checkbox" />
            </div>
        </div>
        <div class="row">
            <label class="col-xs-2 control-label" for="remark">备注:</label>
            <div class="col-xs-10">
                <input class="form-control" id="remark" name="remark" maxlength="100" type="text" required />
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