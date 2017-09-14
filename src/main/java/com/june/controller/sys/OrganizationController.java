/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.EasyTreeData;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.dto.sys.Organization;
import com.june.service.sys.IOrganizationService;
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

/**
 * @author june email:359546407@qq.com
 * @version 1.0
 * @since 1.0
 */@Controller
@RequestMapping("/organization")
public class OrganizationController {
	@Autowired
	private IOrganizationService service;
	
	@RequestMapping("/list")
	public ModelAndView getOrganizationList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("sys/org/list");
		List<Organization> lst = service.getOrganizationList();
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

	@RequestMapping("/combo")
	@ResponseBody
	public String getOrganizationCombo() throws JsonProcessingException {
		return JsonUtils.list2Json(service.getOrganizationTree());
	}
	@RequestMapping("/tree")
	@ResponseBody
	public String getOrganizationTree2() throws JsonProcessingException {
		//return JsonUtils.list2Json(service.getOrganizationTree());
		EasyTreeData easyTreeData = new EasyTreeData();
		return easyTreeData.makeTreeData(service.getOrganizationTree(),"0");
	}

	@RequestMapping("/add")
	public String addOrganization(ModelMap bt) {
		
		bt.addAttribute("dto", 0);
		return "sys/org/edit";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView updateOrganization(@PathVariable("id")int id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sys/org/edit");
		//int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
		try {
			mav.addObject("dto", JsonUtils.objectToJson(service.getOrganizationById(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Organization dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		service.saveOrganization(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> delOrganization(@PathVariable("id")int id,HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		//int id = Integer.parseInt(PageUtils.getString(data.get("id")));
		service.delOrganizationById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
}