/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.dto.sys.Users;
import com.june.service.sys.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private IUsersService service;
	
	@RequestMapping("/list")
	public ModelAndView getUsersList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("sys/users/list");
		List<Users> lst = service.getUsersList();
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
	
	@RequestMapping("/add/{orgid}")
	public String addUsers(@PathVariable("orgid")int orgid, ModelMap bt) {
		bt.addAttribute("orgid",orgid);
		bt.addAttribute("dto", 0);
		return "sys/users/edit";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView updateUsers(@PathVariable("id")int id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sys/users/edit");
		//int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
		try {
			mav.addObject("dto", JsonUtils.objectToJson(service.getUsersById(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Users dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		service.saveUsers(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}

	@RequestMapping("/saveRole")
	@ResponseBody
	public Map<String,Object> saveRole(final int uid,final int[] rids){
		List<Map<String,Object>> lst = new ArrayList<>();
		for (int i=0;i<rids.length;i++){
			Map<String,Object> map= new HashMap<String,Object>();
			map.put("uid",uid);
			map.put("rid",rids[i]);
			lst.add(map);
		}
		service.saveRoles(lst);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> delUsers(@PathVariable("id")int id,HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		//int id = Integer.parseInt(PageUtils.getString(data.get("id")));
		service.delUsersById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
}