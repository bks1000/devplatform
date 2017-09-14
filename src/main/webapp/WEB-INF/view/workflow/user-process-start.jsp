<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/5
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.june.dto.form.Webform" %>
<html>
<head>
    <title>启动流程</title>
    <jsp:include page="../base/jscss.jsp"/>
    <jsp:include page="../base/global.jsp"/>
    <style>
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            //设置表格样式
            $("table").attr("class","table table-bordered table-hover");
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

                $.post('${ctx}/mywf/run',param,function (data) {
                    this.close();
                })
            });
        });
    </script>
</head>
<body>

    <form id="ff" class="container" role="form">
        <div class="row">
            <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存并启动流程</a>&nbsp;&nbsp;
            <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>
        <div class="row">
            <div class="col-xs-6"></div>
            <label class="col-xs-1">${form.title}</label>
            <div class="col-xs-5"></div>
        </div>
        <input type="hidden" id="boid" name="boid" value="${form.boid}">
        <input type="hidden" id="processid" name="processid" value="${processid}">
        ${form.formhtml}
    </form>
</body>
</html>
