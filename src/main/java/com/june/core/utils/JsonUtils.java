package com.june.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;

/**
 * Java下利用Jackson进行JSON解析和序列化
 * Created by lenovo on 2017/7/31.
 */
public class JsonUtils {
    ////这里使用的是net.sf.json JSON序列化工具
    /*public static Map<String, Object> jsonToMap(String jsonString){

        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }
        HashMap<String, Object> retMap = null;
        try {
            retMap = new HashMap<String, Object>();
            JSONObject json = JSONObject.fromObject(jsonString);
            Map<String, Object> tmpMap = (Map<String, Object>) JSONObject.toBean(json, Map.class);
            for (Map.Entry<String, Object> entry : tmpMap.entrySet()) {
                retMap.put(entry.getKey(), entry.getValue());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }

    public static List<Map<String, Object>> jsonToList(String jsonString){

        if (jsonString == null || "".equals(jsonString)) {
            return null;
        }

        List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();

        JSONArray data = JSONArray.fromObject(jsonString);
        for (int i = 0; i < data.size(); i++) {
            HashMap<String, Object> retMap = new HashMap<String, Object>();

            JSONObject json = (JSONObject) data.get(i);
            Map<String, Object> tmpMap = (Map<String, Object>) JSONObject.toBean(json, Map.class);

            for (Map.Entry<String, Object> entry : tmpMap.entrySet()) {
                retMap.put(entry.getKey(), entry.getValue());
            }
            retList.add(retMap);
        }
        return retList;
    }

    public static String mapToJson(Map<String, Object> map) {
        HashMap<String, Object> retMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            retMap.put(entry.getKey(), entry.getValue());
        }
        JsonConfig jc = new JsonConfig();
        return JSONObject.fromObject(retMap, jc).toString();
    }


    public static String listToJson(List<Map<String, Object>> list) {
        List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            HashMap<String, Object> retMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                retMap.put(entry.getKey(), entry.getValue());
            }
            tmpList.add(retMap);
        }
        JSONArray json = new JSONArray();
        json.addAll(tmpList);
        return json.toString();
    }*/

    /////下面用的是和spring框架相同的json序列化工具

    /**
     * JSON序列化
     * @param lst
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String listToJson(List<T> lst) throws JsonProcessingException {
        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        ObjectMapper mapper = new ObjectMapper();
        String jsonlist = mapper.writeValueAsString(lst);
        return  jsonlist;
    }

    /**
     * JSON序列化
     * @param obj
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String objectToJson(T obj) throws JsonProcessingException {
        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        ObjectMapper mapper = new ObjectMapper();
        String jsonlist = mapper.writeValueAsString(obj);
        return  jsonlist;
    }

    public static String mapToJson(Map<String,Object> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    /**
     * json反序列化为java对象
     * @param json
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonToObject(String json ,Class<T> cls) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T obj = mapper.readValue(json, cls);
        return obj;
    }

    /**
     * json反序列化java对象数组
     * @param listStr
     * @param <T>
     * @return
     * @throws IOException
     */
    public  static <T> List<T> jsonToListObject(String listStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<T> lst = mapper.readValue(listStr,new TypeReference<List<T>>() { });
        return  lst;
    }

    public static JSONObject getJSONObject(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace(System.err);
        }
        return jsonObject;
    }

    /**
     * 根据T类型的字段，从json中找到数据并赋值(可以少写很多get,set了)
     * @param json
     * @param retObj
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> void json2Object(String json ,T retObj) throws IOException {
        JSONObject jobj = JsonUtils.getJSONObject(json);
        Class cls = retObj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field:fields){
            String name = field.getName();
            try {
                Method m = cls.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1),field.getType());
                Object value = jobj.get(name);
                if (null != value && !"".equals(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        m.invoke(retObj, value);
                    } else if ("Date".equals(fieldType)) {
                        Date temp = parseDate(value.toString());
                        m.invoke(retObj, temp);
                    } else if ("Integer".equals(fieldType)
                            || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value.toString());
                        m.invoke(retObj, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value.toString());
                        m.invoke(retObj, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value.toString());
                        m.invoke(retObj, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value.toString());
                        m.invoke(retObj, temp);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 根据T类型的字段，从json中找到数据并赋值(可以少写很多get,set了)
     * @param jobj
     * @param retObj
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> void json2Object(JSONObject jobj ,T retObj) throws IOException {
        Class cls = retObj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field:fields){
            String name = field.getName();
            try {
                Method m = cls.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1),field.getType());
                Object value = jobj.get(name);
                if (null != value && !"".equals(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        m.invoke(retObj, value);
                    } else if ("Date".equals(fieldType)) {
                        Date temp = parseDate(value.toString());
                        m.invoke(retObj, temp);
                    } else if ("Integer".equals(fieldType)
                            || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value.toString());
                        m.invoke(retObj, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value.toString());
                        m.invoke(retObj, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value.toString());
                        m.invoke(retObj, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value.toString());
                        m.invoke(retObj, temp);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                //continue;
            }
        }
    }

    /**
     * 读取json字符串中的某段，并生成dto对象数组
     * @param json
     * @param cls
     * @param key json key
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> json2ObjectList(String json ,Class<T> cls,String key) throws IOException, IllegalAccessException, InstantiationException {
        JSONObject jobj = JsonUtils.getJSONObject(json);
        JSONArray arr = jobj.getJSONArray(key);
        List<T> lst = new ArrayList<T>();
        for (int i=0;i<arr.length();i++){
            T dto = cls.newInstance();
            json2Object(arr.getJSONObject(i),dto);
            lst.add(dto);
        }
        return  lst;
    }
        /**
         * 格式化string为Date
         *
         * @param datestr
         * @return date
         */
        public static Date parseDate(String datestr) {
            if (null == datestr || "".equals(datestr)) {
                return null;
            }
            try {
                String fmtstr = null;
                if (datestr.indexOf(':') > 0) {
                    fmtstr = "yyyy-MM-dd HH:mm:ss";
                } else {
                    fmtstr = "yyyy-MM-dd";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(fmtstr);
                return sdf.parse(datestr);
            } catch (Exception e) {
                return null;
            }
        }
    }
