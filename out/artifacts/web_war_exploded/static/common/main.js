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
        $("li [name='nav']").each(function (i) {
            $(this).removeAttr("class");
        });
        $(obj).attr("class", "active");
    },
    loadMenu:function(id,text){
        $(".panel-title").text(text);
        $.get(ctx+'/menu',{"menuid":id},function (data) {
            $.each(data, function(i, n) {//加载父类节点即一级菜单
                $('#acc').accordion('add', {
                    title : n.name,
                    iconCls : '',
                    selected : (i==0),
                    content : '<div style="padding:20px"><ul>'+
                    '</ul></div>',
                });
            });
        });
    }
};