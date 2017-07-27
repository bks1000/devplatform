<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="base/jscss.jsp"/>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',href:'${ctx}/navbar'" style="height:30px">
        </div>
        <div data-options="region:'south',split:false" style="height:20px;">
            develop platform
        </div>
        <div data-options="region:'west',split:true" title="" style="width:260px;">
            <ul id="menutree"></ul>
        </div>
        <div data-options="region:'center',border:false" id="tt" class="easyui-tabs">

        </div>
    </div>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            //添加主界面
            OP.addPanel('${ctx}/chart',"主页");
            //树形菜单点击动作，打开新标签，加载
            $('#menutree').tree({
                url:'${ctx}/home/menu',
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
            });
        });
        //操作类
        var OP = {
            addPanel:function(url,name){
                //判断是否存在同名tab，存在选中，不存在新增
                var tab = $('#tt').tabs('exists',name);
                if(!tab){
                    var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                    $('#tt').tabs('add',{
                        title:name,
                        //href:url,
                        content:content,
                        closable:true,
                        selected:true
                    });
                }else{
                    $('#tt').tabs('select',name);
                }
            },
            addPanel2:function(url,name){
                //全部关掉，然后打开选中的
                var tabs = $('#tt').tabs('tabs');
                console.log(tabs.length);
                for(var i=0;i<tabs.length;i++){
                    $('#tt').tabs('close',i);
                }
                $('#tt').tabs('add',{
                    title:name,
                    href:url,
                    closable:true,
                    selected:true
                });
            },
            getPanel:function(name){
                return $('#tt').tabs('getTab',name);
            }
        };
    </script>
</body>
</html>
