<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../base/jscss.jsp"/>
    <%--<link rel="Stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-switch.css"/>
    <script src="${ctx}/static/bootstrap/js/bootstrap-switch.min.js" type="text/javascript"></script>--%>
    <script src="${ctx}/static/common/table.js" type="text/javascript"></script>
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
                var paramMeta = table.getDatas("tb");
                //为sn赋值
                for(var i=0;i<paramMeta.length;i++){
                    paramMeta[i].sn=i;
                }
                param.attributeList = paramMeta;
                console.log(JSON.stringify(param));
                $.post('${ctx}/forminfo/save',JSON.stringify(param),function (data) {
                    parent.OP.closeDialog();
                })
            });
            $('#btncancel').bind('click', function(){
                parent.OP.closeDialog();
            });

            if (dto!=null && dto!=undefined && dto!=0){
                $('#ff').form('setData',dto,"ff");
                //为子列表赋值
                var attributeList = dto.attributeList;
                for(var i=0;i<attributeList.length;i++){
                    var row = copy();
                    table.setRowData(row,attributeList[i]);
                }
            }
        });
        function copy() {
            var a = `<tr>
					<td><input type="hidden" id="ffid" name="ffid"/>
					<input type="hidden" id="fid" name="fid"/>
					<input type="hidden" id="isnew" name="isnew" value="true"/>
					<input type="text" id="note" name="note" required/></td>
	                <td><input type="text" id="ffname" name="ffname" required/></td></td>
	                <td><input id="required" name="required" type="checkbox" checked value="1" onclick="chk(this)"/></td>
	                <td><select id="datatype" name="datatype" class="span2">
	                		<option value="varchar">字符串</option>
	                		<option value="int">数字</option>
						</select>
	                </td>
	                <td><input id="datalength" name="datalength" type="text" required/></td>
	                <td><input id="defaultvalue" name="defaultvalue" type="text"/></td>
	                <td>
	                	<input id="sn" name="sn" type="hidden"/>
	                	<span class="glyphicon glyphicon-chevron-up" aria-hidden="true" onclick="up(this)"></span>
	                	<span class="glyphicon glyphicon-chevron-down" onclick="down(this)" ></span>
	                </td>
	                <td><span class="glyphicon glyphicon-remove" onclick="del(this)"></span></td>
				</tr>`;
            $("#tb").append(a);
            $("#meta").validate();
            //return $("tr").eq(-i-1)[0]; //$('tr').last()[0];
            return $('tr').last()[0];
        }
        function getdata() {
            var param = $('#ff').form('getData',true);
            var paramMeta = table.getDatas("tb");
            //为sn赋值
            for(var i=0;i<paramMeta.length;i++){
                paramMeta[i].sn=i;
            }
            param.attributeList = paramMeta;
            console.log(param);
        }
        function chk(obj) {
            if(obj.checked==true){
                obj.value=1;
            }else {
                obj.value=0;
            }
        }
        /*以下是字段行操作*/
        function down(obj) {
            var row = obj.parentNode.parentNode;
            var nextRow = $(row).next();//查找下一行
            $(row).insertAfter(nextRow);
        }
        function up(obj) {
            var row = obj.parentNode.parentNode;//当前行
            var prevRow = $(row).prev();//查找上一行
            $(row).insertBefore(prevRow);//把所有匹配的元素插入到另一个、指定的元素元素集合的前面。
        }
        function del(obj) {
            var row = obj.parentNode.parentNode;
            //console.log(row);
            $(row).empty();//删除匹配的元素集合中所有的子节点。
        }
    </script>
    <style>
        #tb td > span{border: solid;}
    </style>
</head>
<body>
    <form id="ff" class="container" role="form">
        <div class="row">
            <!--<span class="col-xs-8"></span>-->
            <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
            <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>
        <input type="hidden" id="fid" name="fid">
		<div class="row">
            <label class="col-xs-2 control-label" for="tid">类型ID:</label>
            <div class="col-xs-10">
                <input class="form-control" id="tid" name="tid" maxlength="10" type="text" placeholder="请输入类型id" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="fname">实体名称:</label>
            <div class="col-xs-10">
                <input class="form-control" id="fname" name="fname" maxlength="50" type="text" placeholder="请输入实体名称" required />
            </div>
        </div>
		<div class="row">
            <label class="col-xs-2 control-label" for="fmark">实体描述:</label>
            <div class="col-xs-10">
                <input class="form-control" id="fmark" name="fmark" maxlength="200" type="text" placeholder="请输入实体描述" required />
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
                <td>注释</td>
                <td>名称</td>
                <td>必填</td>
                <td>数据类型</td>
                <td>属性长度</td>
                <td>默认值</td>
                <td>排序</td>
                <td>操作</td>
            </thead>
        </table>
    </form>
</body>
</html>