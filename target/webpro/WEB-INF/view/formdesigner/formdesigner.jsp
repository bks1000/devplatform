<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/8/22
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表单设计器</title>
    <jsp:include page="../base/jscss.jsp"/>
    <script type="text/javascript" src="${ctx}/static/ueditor/ueditor/ueditor.config.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/static/ueditor/ueditor/ueditor.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/static/ueditor/ueditor/lang/zh-cn/zh-cn.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/static/ueditor/ueditor/formdesign/leipi.formdesign.v4.js" charset="utf-8"></script>

    <script type="text/javascript">
        //替换html模板变量(好用)
        function formatTemplate(dta, tmpl) {
            var format = {
                name: function(x) {
                    return x
                }
            };
            return tmpl.replace(/{(\w+)}/g, function(m1, m2) {
                if (!m2)
                    return "";
                return (format && format[m2]) ? format[m2](dta[m2]) : dta[m2];
            });
        }
        var dto = ${dto};
        var fields = null;
        $(document).ready(function () {
            $("#fields").append('<li>'+dto.title+'</li>');
            $.post("${ctx}/webform/ffields/"+dto.boid,function (data) {
                fields = data;
                var tmp = `<li><a href="javascript:void(0);" onclick="formDesign.exec('{tp}','{idx}');" class="btn btn-link">{nm}</a></li>`;
                for (var i=0;i<data.length;i++){
                    var tp = '';
                    var nm = data[i].note;
                    switch(data[i].datatype){
                        case 'varchar':
                            tp='text';
                            break;
                        case 'int':
                            tp='text';
                            break;
                    }
                    $("#fields").append(formatTemplate({tp:tp,nm:nm,idx:i},tmp));
                }
            });
            $('#btnsave').bind('click', function(){
                //console.log(JSON.stringify(param));
                var param = formEditor.getContent();
                param = html_encode(param);
                $.post('${ctx}/webform/savehtml',{id:dto.wid,html:param},function (data) {
                    parent.OP.closeDialog();
                })
            });
            $('#btncancel').bind('click', function(){
                parent.OP.closeDialog();
            });
        });
        // 转义字符为HTML实体，相当于PHP的htmlspecialchars()函数
        function html_encode(str)
        {
            var s = "";
            if (str.length == 0) return "";
            s = str.replace(/&/g, "&amp;");
            s = s.replace(/</g, "&lt;");
            s = s.replace(/>/g, "&gt;");
            s = s.replace(/\"/g, "&quot;");
            return s;
        }
    </script>

</head>
<body class="container">
    <div class="row">
        <div class="col-xs-1">
            <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
            <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>
        <div class="col-xs-1">

        </div>
        <div class="col-xs-10"></div>
    </div>
    <div class="row">
        <div class="col-xs-4">
            <ul id="fields" class="unstyled">

            </ul>
        </div>
        <div class="col-xs-8">
            <script id="myFormDesign" type="text/plain" style="width:100%;"></script>
        </div>
    </div>


    <script type="text/javascript">
        var formEditor = UE.getEditor('myFormDesign',{
            //allowDivTransToP: false,//阻止转换div 为p
            toolleipi:false,//是否显示，设计器的 toolbars
            textarea: 'design_content',
            //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
            toolbars:[[
                'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|', 'fontfamily', 'fontsize', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', '|', 'inserttable', 'deletetable',  'mergecells',  'splittocells']],
            //focus时自动清空初始化时的内容
            //autoClearinitialContent:true,
            //开启字数统计
            wordCount:true,
            //关闭elementPath
            elementPathEnabled:false,
            //默认的编辑区域高度
            initialFrameHeight:500
            ,iframeCssUrl:"${ctx}/static/bootstrap/css/bootstrap.css" //引入自身 css使编辑器兼容你网站css
            //更多其他参数，请参考ueditor.config.js中的配置项
        });
        var formDesign={
            /*执行控件*/
            exec : function (method,idx) {
                UE.param = fields[idx];//设置参数，供插件生成表单元素使用。
                formEditor.execCommand(method);
            },
        };
    </script>
</body>
</html>
