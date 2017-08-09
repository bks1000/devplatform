<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="base/jscss.jsp"/>
    <script type="text/javascript" src="${ctx}/static/common/main.js"></script>
    <style type="text/css">
        .panel-footer{padding: 0px;}
    </style>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',height:51,href:'${ctx}/navbar'" style="height:51px;">
        </div>
        <div data-options="region:'south',split:false" style="height:20px;">
            develop platform
        </div>
        <div data-options="region:'west',split:true" title="" style="width:260px;">
            <!--<ul id="menutree"></ul>-->
            <div class="panel-header" style="width: 100%">
                <div class="panel-title"></div>
                <div class="panel-tool"></div>
            </div>
            <div id="acc" class="easyui-accordion" data-options="fit:true,border:false,nimate:true,lines:true"></div>
        </div>
        <div data-options="region:'center',border:false,fit:true" id="tt" class="easyui-tabs">

        </div>
    </div>
    <div id="dd" style="overflow-x:hidden;"></div>
    <!--<div id="tools">
        <a class="icon-add" onclick="javascript:alert('add')"></a>
        <a class="icon-edit" onclick="javascript:alert('edit')"></a>
    </div>
    <div id="footer" style="height: 30px;">
        <a id="btnsave" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        <a id="btncancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
    </div>-->
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            //添加主界面
            //OP.addPanel('${ctx}/chart',"主页");
            //树形菜单点击动作，打开新标签，加载
            //tree如何绑定自己的数据段
            /*$('#menutree').tree({ //https://zhidao.baidu.com/question/532380131.html
                url:'${ctx}/menu',
                method:'get',
                animate:true,
                onClick: function(node){
                    var url = node.url;
                    var name = node.name;
                    if (name != '菜单管理'){
                        url = url+"/list";
                    }
                    OP.addPanel(url,text);
                }
            });*/
        });

    </script>
</body>
</html>
