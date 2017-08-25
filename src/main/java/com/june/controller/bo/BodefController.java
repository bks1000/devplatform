/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.dto.bo.Bodef;
import com.june.service.bo.IBodefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author june email:359546407@qq.com
 * @version 1.0
 * @since 1.0
 */@Controller
@RequestMapping("/bodef")
public class BodefController {
	@Autowired
	private IBodefService service;
	
	@RequestMapping("/list")
	public ModelAndView getBodefList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("bo/bodef/list");
		List<Bodef> lst = service.getBodefList();
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
	public String addBodef(ModelMap bt) {
		
		bt.addAttribute("dto", 0);
		return "bo/bodef/edit";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView updateBodef(@PathVariable("id")String id ,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("bo/bodef/edit");
		//String id = PageUtils.getString(PageUtils.getParameters(request).get("id"));
		try {
			mav.addObject("dto", JsonUtils.objectToJson(service.getBodefById(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Bodef dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		if (dto.getBoid()==null || "".equals(dto.getBoid())){
			dto.setBoid(UUID.randomUUID().toString());
			dto.setRs("1");
		}
		service.saveBodef(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> delBodef(@PathVariable("id")String id, HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		//String id = PageUtils.getString(data.get("id"));
		service.delBodefById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
}