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