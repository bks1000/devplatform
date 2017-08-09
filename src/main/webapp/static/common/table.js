/**
 * Created by lenovo on 2017/8/7.
 */

//tabel操作类
var table={
    /**
     * 获取table所有行，形成JSON数组
     * @param tname
     */
    getDatas:function (tname) {
        var rows=[];
        $("#"+tname +" tr").each(function (i) {
            if(i==0){
                //跳过table head
                return true;
            }
            var row = table.getRowData($(this),true);
            row && rows.push(row);
        });
        return rows;
    },
    /**
     * 获取单行数据
     * @param tr
     * @param params
     * @returns {*}
     */
    getRowData:function (tr,params) {
        var formArray = $(tr[0]).find("input");
        var selectArray = $(tr[0]).find("select");
        //console.log(formArray.length);
        if(formArray.length==0){
            return 0;
        }
        var oRet={};
        for (var i=0; i<formArray.length; i++) {
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
        for (var i=0; i<selectArray.length; i++) {
            oRet[selectArray[i].name] = selectArray[i].value;
        }
        return oRet;
    },
    /**
     * 设置单行数据(因为id，name是重复，所以只能在tr中找控件)
     * @param tr
     * @param params
     * @returns {number}
     */
    setRowData:function (tr,params) {
        /*var formArray = $(tr).find("input");
        var selectArray = $(tr).find("select");
        //console.log(formArray.length);
        if(formArray.length==0){
            return 0;
        }
        for (var i=0; i<formArray.length; i++) {
            //formArray[i].value = params[formArray[i].name];
            $('#'+formArray[i].name).val(params[formArray[i].name]);
        }
        for (var i=0; i<selectArray.length; i++) {
            //selectArray[i].value=params[selectArray[i].name];
            $('#'+selectArray[i].name).val(params[formArray[i].name]);
        }*/
        $.each($(tr).find("input"),function (i, n) {
            //console.log(n);
            $(n).val(params[n.name]);
            $(n).attr("type")=='checkbox' && $(n).attr("checked",params[n.name]=="1");
        });
        $.each($(tr).find("select"),function (i,n) {
            $(n).val(params[n.name]);
        })
    }
};
