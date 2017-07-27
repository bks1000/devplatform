/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.june.dto.sys.Menu;
import com.june.service.sys.IMenuService;
import com.june.core.utils.*;

/**
 * @author june email:359546407@qq.com
 * @version 1.0
 * @since 1.0
 */@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private IMenuService service;
	
	@RequestMapping("/list")
	public ModelAndView getBookList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("sys/menu/list");
		List<Menu> lst = service.getMenuList();
		mav.addObject("data", lst);
		return mav;
	}
	
	@RequestMapping("/add")
	public String addMenu(ModelMap bt) {
		
		bt.addAttribute("dto", new Menu());
		return "sys/menu/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateMenu(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sys/menu/edit");
		String id = (PageUtils.getString(PageUtils.getParameters(request).get("id")));
		mav.addObject("dto", service.getMenuById(id));
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Menu dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		service.saveMenu(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> delMenu(HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		String id = PageUtils.getString(data.get("id"));
		service.delMenuById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
}