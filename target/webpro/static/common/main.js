/**
 * Created by lenovo on 2017/7/28.
 */

//操作类
var OP = {
    /**
     * 点选菜单添加选项卡
     * @param url 要加载的地址
     * @param name 选项卡名称
     */
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
    /**
     * 不再使用！
     * @param url
     * @param name
     */
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
    /**
     * 根据选项卡名称，获取选项卡
     * @param name
     * @returns {jQuery}
     */
    getPanel:function(name){
        return $('#tt').tabs('getTab',name);
    },
    /**
     * 顶部导航栏点击状态设置
     * @param obj
     */
    setState:function (obj) {
        $("li[name='nav']").each(function (i) {
            $(this).removeAttr("class");
        });
        $(obj).attr("class", "active");
    },
    /**
     * 根据模块ID加载菜单
     * @param id
     * @param text
     */
    loadMenu:function(id,text){
        var panels = $('#acc').accordion('panels');
        for(var i=0;i<panels.length;i++) {
            $('#acc').accordion('remove', i);
        }
        $(".panel-title").text(text);
        $.get(ctx+'/menu',{"menuid":id},function (data) {
            $.each(data, function(i, n) {//加载父类节点即一级菜单

                if(n.parentid==id){
                    $('#acc').accordion('add', {
                        title : n.name,
                        iconCls : '',
                        selected : (i==0),
                        content : '<div><ul class="list-group" id="'+n.id+'">'+
                        '</ul></div>',
                    });
                }else{
                    var url = n.url;
                    $('#'+n.parentid).append('<li class="list-group-item"><a onclick="OP.addPanel(\''+ctx+  n.url +'\',\''+n.name+'\')" target='+ n.url +'>'+n.name+'</a></li>');
                }
            });
        });
    },
    showDialog:function (obj) {
        var content = '<iframe src="' + obj.href + '" width="100%" height="99%" frameborder="0" style="overflow-x: hidden"></iframe>';
        //var boarddiv = '<div id="msgwindow" title="' + title + '"></div>'//style="overflow:hidden;"可以去掉滚动条
        //$(document.body).append(boarddiv);
        top.$('#dd').window({
            width:obj.width,
            height:obj.height,
            maximized:obj.maximized,
            modal:obj.modal?obj.modal:true,
            title:obj.title,
            closable:obj.closable?obj.closable:true,
            resizable:obj.resizable?obj.resizable:true,
            //href:obj.href,
            content: content,
            onClose:obj.onClose,
            //tools:'#tools',
            //footer:'#footer'
        });
        /*$('#btnsave').bind('click', function(){
            alert('save');
        });
        $('#btncancel').bind('click', function(){
            alert('canel');
        });*/
    },
    closeDialog:function () {
        top.$('#dd').window('close');
    }
};