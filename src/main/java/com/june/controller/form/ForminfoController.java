/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.core.utils.ReflectUtil;
import com.june.dto.form.Formfieldmeta;
import com.june.dto.form.Forminfo;
import com.june.service.form.IFormfieldmetaService;
import com.june.service.form.IForminfoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author june email:359546407@qq.com
 * TODO:说明:hibernate一对多没有使用，下次用用！
 * @version 1.0
 * @since 1.0
 */@Controller
@RequestMapping("/forminfo")
public class ForminfoController {
	@Autowired
	private IForminfoService service;

	@Autowired
	private IFormfieldmetaService ffservice;
	
	@RequestMapping("/list")
	public ModelAndView getForminfoList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("form/forminfo/list");
		List<Forminfo> lst = service.getForminfoList();
		//将List<Menu>序列化为JSON字符串，供datagrid使用。
		String data="";
		try {
			data = JsonUtils.listToJson(lst);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		mav.addObject("data", data);
		return mav;
	}
	
	@RequestMapping("/add")
	public String addForminfo(ModelMap bt) {
		
		bt.addAttribute("dto", 0);
		return "form/forminfo/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateForminfo(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("form/forminfo/edit");
		String id = PageUtils.getString(PageUtils.getParameters(request).get("id"));
		try {
			Forminfo fi = service.getForminfoById(id);
			List<Formfieldmeta> ff = ffservice.getFormfieldmetaByFid(id);
			Map<String,Object> dto = new HashMap<String,Object>();

			//主表
			ReflectUtil.fillDto2Map(fi,dto);
			//子表
			dto.put("attributeList",ff);

			mav.addObject("dto", JsonUtils.objectToJson(dto));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		try {
			String reqData = PageUtils.getRequestBody(request); //request.getQueryString();
			JSONObject obj = JsonUtils.getJSONObject(reqData);
			//Forminfo fi = JsonUtils.jsonToObject(JsonUtils.mapToJson(params),Forminfo.class);
			//List<Formfieldmeta> lst = JsonUtils.jsonToListObject(params.get("attributeList").toString());

			/*String fid = obj.getString("fid");
			String tid = obj.getString("tid");
			String fmark = obj.getString("fmark");
			String fname = obj.getString("fname");
			String rs = "1";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String ts = df.format(new Date());*/

			Forminfo fi = new Forminfo();
			JsonUtils.json2Object(reqData,fi);
			if (null == fi.getFid() || "".equals(fi.getFid())){
				fi.setFid(java.util.UUID.randomUUID().toString());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				fi.setTs(df.format(new Date()));
				fi.setRs("1");
				fi.setIscreate("0");
			}
			List<Formfieldmeta> lst = JsonUtils.json2ObjectList(reqData,Formfieldmeta.class,"attributeList");
			for(Formfieldmeta ffm : lst){
				if (null == ffm.getFfid() || "".equals(ffm.getFfid())){
					ffm.setFfid(java.util.UUID.randomUUID().toString());
				}
				if (null == ffm.getFid() || "".equals(ffm.getFid())){
					ffm.setFid(fi.getFid());
				}
			}

			service.saveForminfo(fi,lst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> delForminfo(HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		String id = PageUtils.getString(data.get("id"));
		service.delForminfoById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 1);
		return ret;
	}

	/**
	 * 生成实体表
	 * @param request
	 * @return
	 */
	@RequestMapping("/build")
	public Map<String, Object> buildTable(HttpServletRequest request){
		Map<String, Object> data = PageUtils.getParameters(request);
		String id = PageUtils.getString(data.get("id"));
		service.buildTable(id);

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 1);
		return ret;
	}
}