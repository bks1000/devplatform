package com.june.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageUtils {

	/**
	 * @param request 请求中的request
	 * @return 返回Map获取request中的参数
	 */
	public static Map<String,Object> getParameters(HttpServletRequest request){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		Enumeration<String> names = request.getParameterNames();
		
		while(names.hasMoreElements()){
			String key = names.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * 判断对象是null或者""
	 * @param o
	 * @return true: "",false:"***"
	 */
	public static String getString(Object o) {
		if(o == null || "null".equals(o.toString())||"".equals(o.toString())){
			return "";
		}else {
			return o.toString();
		}
	}

	/**
	 * 获取请求的body.
	 * Content-Type: application/x-www-form-urlencoded; charset=UTF-8
	 * 这种情况下，只能用原始的流的方式读取数据。
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestBody(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();

		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		return  wholeStr;
	}
}
