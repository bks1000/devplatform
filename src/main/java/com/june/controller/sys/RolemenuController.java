/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.dto.sys.Rolemenu;
import com.june.service.sys.IRolemenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author june email:359546407@qq.com
 * @version 1.0
 * @since 1.0
 */@Controller
@RequestMapping("/rolemenu")
public class RolemenuController {
	@Autowired
	private IRolemenuService service;
	
	@RequestMapping("/list")
	public ModelAndView getRolemenuList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("sys/rolemenu/list");
		List<Rolemenu> lst = service.getRolemenuList();
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
	public String addRolemenu(ModelMap bt) {
		
		bt.addAttribute("dto", 0);
		return "rolemenu/edit";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView updateRolemenu(@PathVariable("id")String id,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("rolemenu/edit");
		//int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
		try {
			mav.addObject("dto", JsonUtils.objectToJson(service.getRolemenuById(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(final int rid,final String[] mids) {

		List<Map<String,Object>> lst = new ArrayList<>();
		for (int i=0;i<mids.length;i++){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("rid",rid);
			map.put("mid",mids[i]);
			lst.add(map);
		}
		service.saveRolemenu(lst);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> delRolemenu(@PathVariable("id")String id,HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		//int id = Integer.parseInt(PageUtils.getString(data.get("id")));
		service.delRolemenuById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}

	@RequestMapping("/getmenus/{rid}")
	@ResponseBody
	public String getMenuByRid(@PathVariable("rid")int id) throws JsonProcessingException {
		List<Map<String,Object>> lst = service.getRolemenuById(id);
		return  JsonUtils.listToJson(lst);
	}
}