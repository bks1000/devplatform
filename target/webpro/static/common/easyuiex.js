//easyui扩展

/**
 * 树形扩展，返回过滤过的数据进行展示。
 * @param  data：加载的原始数据。
 * @returns 返回数据是标准树格式。
 */
var convertTreeData = function(data){
    var nodes = [];
    for(var i=0; i<data.length; i++){
        var row = data[i];
        nodes.push({
            id:data[i].id,  // 这里绑定你的字段
            text:data[i].name, // 这里绑定你的字段
            //state:data.XX,// 这里绑定你的字段
            //checked:data.checked,// 这里绑定你的字段
            attributes:data// 这里绑定你的字段
        });
    }
    return nodes;
}
$.fn.tree.defaults.loadFilter = convertTreeData;

/**
 * From扩展
 *
 * @param {Object} jq
 * @param {Object} params 设置为true的话，会把string型"true"和"false"字符串值转化为boolean型。
 */
$.extend($.fn.form.methods, {
    /**
     * jquery serializeArray 的bug修补方法
     * http://www.cnblogs.com/tangge/p/6554891.html
     * @param jq
     * @param fname form id
     */
    ghostSerialize:function (jq, fname) {
        var formArray = jq.serializeArray();
        var radio = $('input[type=radio],input[type=checkbox]', $("#"+fname));
        var temp = {};
        $.each(radio, function () {
            if (!temp.hasOwnProperty(this.name)) {
                if ($("input[name='" + this.name + "']:checked").length == 0) {
                    temp[this.name] = "";
                    formArray.push({name: this.name, value: ""});
                }
            }
        });
        //console.log(formArray );
        return formArray;
    },
    /**
     * getData 获取数据接口
     * @param jq
     * @param params
     * @returns {{}}
     */
    getData: function(jq, params){
        var formArray = jq.serializeArray();
        var oRet = {};
        for (var i in formArray) {
            if (typeof(oRet[formArray[i].name]) == 'undefined') {
                if (params) {
                    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
                }
                else {
                    oRet[formArray[i].name] = formArray[i].value;
                }
            }
            else {
                if (params) {
                    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
                }
                else {
                    oRet[formArray[i].name] += "," + formArray[i].value;
                }
            }
        }
        return oRet;
    },
    /**
     * 为form表单赋值
     * @param jq
     * @param params Json数据对象
     * @param fid    from id
     */
    setData: function(jq, params ,fid){
        var formArray = this.ghostSerialize(jq,fid);//jq.serializeArray();
        for (var i in formArray) {
            var iid = formArray[i].name;
            if (iid == "rs"){
                //$('#'+formArray[i].name).val(params[iid]=="1"?"on":"off");
                $("input[name='rs']").each(function(){
                    this.checked="1"?true:false;
                });
                continue;
            }
            $('#'+formArray[i].name).val(params[iid]);
        }
    }
});

/**
 * 为jquery-validate组件添加汉化提示
 */
$.extend($.validator.messages, {
    required: "必选字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: $.validator.format("请输入一个最大为 {0} 的值"),
    min: $.validator.format("请输入一个最小为 {0} 的值")
});
/**
 * 消息窗口按钮文本
 */
$.extend($.messager.defaults,{
    ok:"确定",
    cancel:"取消"
});
