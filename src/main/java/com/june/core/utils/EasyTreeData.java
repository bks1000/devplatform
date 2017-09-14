package com.june.core.utils;

/**
 * Created by lenovo on 2017/9/7.
 */

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 将List<Map>转json 字符串，符合easyui tree 数据结构
 */
public class EasyTreeData {

    /**
     * 生成树形数据结构
     * @param lst 包含 id,text pid
     * @param pid 0 从根级开始构造
     * @return
     */
    private List<Map<String,Object>> makeData(List<Map<String,Object>> lst,String pid){
        List<Map<String,Object>> data = lst;
        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();

        List<Map<String,Object>> root = data.stream().filter(x->(x.get("pid").toString()).equals(pid)).collect(Collectors.toList());
        if (root.size()>0){
            result.addAll(root);
            for (Map<String,Object> r : root) {
                 List<Map<String,Object>> child = makeData(data,r.get("id").toString());
                 if (child.size()>0){
                    r.put("children",child);
                 }
            }
        }
        return result;
    }

    /**
     * 生成树形数据结构
     * @param lst 包含 id,text pid
     * @param pid 0 从根级开始构造
     * @return
     */
    public String makeTreeData(List<Map<String,Object>> lst ,String pid) throws JsonProcessingException {
        List<Map<String,Object>> result =  this.makeData(lst,pid);
        return  JsonUtils.listToJson(result);
    }
}
