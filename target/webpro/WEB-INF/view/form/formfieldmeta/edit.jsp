<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <script type="text/javascript">
    	//var dto = $_{dto}; //TODO:生成时，会导致错误，故添加下划线，使用时修改！
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
                $.post('$_{ctx}/formfieldmeta/save',param,function (data) {
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
		<div class="row">
            <label class="col-xs-2 control-label" for="Ffid">Ffid:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Ffid" name="Ffid" maxlength="40" type="text" placeholder="请输入Ffid" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Fid">Fid:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Fid" name="Fid" maxlength="40" type="text" placeholder="请输入Fid" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Note">Note:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Note" name="Note" maxlength="100" type="text" placeholder="请输入Note" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Ffname">Ffname:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Ffname" name="Ffname" maxlength="50" type="text" placeholder="请输入Ffname" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Required">Required:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Required" name="Required" maxlength="1" type="text" placeholder="请输入Required" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Datatype">Datatype:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Datatype" name="Datatype" maxlength="30" type="text" placeholder="请输入Datatype" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Datalength">Datalength:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Datalength" name="Datalength" maxlength="10" type="text" placeholder="请输入Datalength" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Defaultvalue">Defaultvalue:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Defaultvalue" name="Defaultvalue" maxlength="50" type="text" placeholder="请输入Defaultvalue" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="Sn">Sn:</label>
            <div class="col-xs-10">
                <input class="form-control" id="Sn" name="Sn" maxlength="10" type="text" placeholder="请输入Sn" required />
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