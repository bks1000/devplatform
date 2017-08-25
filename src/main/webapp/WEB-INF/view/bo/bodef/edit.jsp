<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
    	var dto = ${dto};
    	var entity= null;
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
                param.rs = "on"==param.rs?1:0;
                
              	//TODO:生成时，会导致错误，故添加下划线，使用时修改！
                $.post('${ctx}/bodef/save',param,function (data) {
                    parent.OP.closeDialog();
                });
            });
            $('#btncancel').bind('click', function(){
                parent.OP.closeDialog();
            });

            if (dto!=null && dto!=undefined && dto!=0){
                $('#ff').form('setData',dto,"ff");
            }

            $('#btnSearch').bind('click', function(){
                parent.OP.showDialog2({
                    title:'选择主实例',
                    href:'${ctx}/comm/forminfo',
                    width:settings.dialogWidth,
                    height:settings.dialogHeith,
                    maximized:false,
                    onClose:function () {
                        //entity = this.parent.middleman;//这里this还是dd2
                        entity = top.middleman;
                        if(entity) {
                            $("#fid").val(entity.id);
                            $("#fname").val(entity.name);
                            $('#fmark').val(entity.text);
                            $('#fmark2').text(entity.text);
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
        function copy() {
            alert("暂不实现！");
        }
    </script>
</head>
<body>
    <form id="ff" class="container" role="form">
        <div class="row">
            <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
            <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>
        <input id="boid" value="boid" type="hidden"/>
		<div class="row">
            <label class="col-xs-2 control-label" for="bomark">描述:</label>
            <div class="col-xs-10">
                <input class="form-control" id="bomark" name="bomark" maxlength="200" type="text" placeholder="请输入描述" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="boname">别名:</label>
            <div class="col-xs-10">
                <input class="form-control" id="boname" name="boname" maxlength="50" type="text" placeholder="请输入别名" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="fid">主实例:</label>
            <div class="col-xs-10">
                <input id="fid" name="fid" type="hidden">
                <input id="fname" name="fname" type="hidden">
                <input id="fmark" name="fmark" type="hidden">
                <span id="fmark2"></span>
                <a id="btnSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'">选择</a>
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="tid">分类:</label>
            <div class="col-xs-10">
                <input class="form-control" id="tid" name="tid" required />
            </div>
        </div>
    </form>

    <form id="meta" class="container" role="form">
        <div class="row">
            <input type="button" value="添加" onclick="copy()" />
            <input type="button" value="getdata测试" onclick="getdata()" />
        </div>
        <table id="tb" width="100%" class="table table-striped table-bordered">
            <thead>
            <td>类型</td>
            <td>描述</td>
            <td>别名</td>
            <td>操作</td>
            </thead>
        </table>
    </form>
</body>
</html>