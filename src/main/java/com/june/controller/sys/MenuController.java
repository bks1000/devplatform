/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.dto.sys.Menu;
import com.june.service.sys.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		List<Menu> lst = service.getMenuList(); //如果直接返回lst对象，view层必须循环处理menu对象
		//将List<Menu>序列化为JSON字符串，供datagrid使用。
		String menus="";
		try {
			menus = JsonUtils.listToJson(lst);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		mav.addObject("data", menus);
		return mav;
	}
	
	@RequestMapping("/add")
	public String addMenu(ModelMap bt) {
		bt.addAttribute("dto", 0);
		return "sys/menu/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateMenu(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sys/menu/edit");
		String id = (PageUtils.getString(PageUtils.getParameters(request).get("id")));
		try {
			mav.addObject("dto", JsonUtils.objectToJson(service.getMenuById(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Menu dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		dto.setTs(df.format(new Date()));
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