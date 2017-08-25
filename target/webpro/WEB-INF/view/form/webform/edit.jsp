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
                //param.rs = "on"==param.rs?1:0;
                if(param.boid==""){
                    alert("请选择业务对象！");
                    return;
                }

                $.post('${ctx}/webform/save',param,function (data) {
                    parent.OP.closeDialog();
                })
            });
            $('#btncancel').bind('click', function(){
                parent.OP.closeDialog();
            });

            if (dto!=null && dto!=undefined && dto!=0){
                $('#ff').form('setData',dto,"ff");
            }

            $('#btnSearch').bind('click', function(){
                parent.OP.showDialog2({
                    title:'选择业务对象',
                    href:'${ctx}/comm/boinfo',
                    width:settings.dialogWidth,
                    height:settings.dialogHeith,
                    maximized:false,
                    onClose:function () {
                        //entity = this.parent.middleman;//这里this还是dd2
                        entity = top.middleman;
                        if(entity){
                            $("#boid").val(entity.id);
                            $("#boname").val(entity.name);
                            $('#bomark').val(entity.text);
                            $('#bomark2').text(entity.text);
                        }
                        top.middleman=null;
                    }
                });
            });

            $('#tid').combobox({
                url:'${ctx}/comm/formtype',
                valueField:'tid',
                textField:'tname',
                width:'200'
            });
        });
    </script>
</head>
<body>
    <form id="ff" class="container" role="form">
		<input type="hidden" id="wid" name="wid"/>
		<div class="row">
            <label class="col-xs-2 control-label" for="tid">表单分类:</label>
            <div class="col-xs-10">
                <input class="form-control" id="tid" name="tid" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="title">表单名称:</label>
            <div class="col-xs-10">
                <input class="form-control" id="title" name="title" maxlength="100" type="text" placeholder="请输入表单名称" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="formkey">别名:</label>
            <div class="col-xs-10">
                <input class="form-control" id="formkey" name="formkey" maxlength="40" type="text" placeholder="请输入表单key" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="wmark">说明:</label>
            <div class="col-xs-10">
                <input class="form-control" id="wmark" name="wmark" maxlength="200" type="text" placeholder="请输入说明" />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="boid">业务对象:</label>
            <div class="col-xs-10">
                <input id="boid" name="boid" type="hidden">
                <input id="boname" name="boname" type="hidden">
                <input id="bomark" name="bomark" type="hidden">
                <span id="bomark2"></span>
                <a id="btnSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'">选择</a>
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