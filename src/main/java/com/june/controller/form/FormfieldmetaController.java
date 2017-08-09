/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.controller.form;

import com.june.core.utils.PageUtils;
import com.june.dto.form.Formfieldmeta;
import com.june.service.form.IFormfieldmetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/formfieldmeta")
public class FormfieldmetaController {
	@Autowired
	private IFormfieldmetaService service;
	
	@RequestMapping("/list")
	public ModelAndView getBookList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("formfieldmeta/list");
		List<Formfieldmeta> lst = service.getFormfieldmetaList();
		mav.addObject("data", lst);
		return mav;
	}
	
	@RequestMapping("/add")
	public String addFormfieldmeta(ModelMap bt) {
		
		bt.addAttribute("dto", 0);
		return "formfieldmeta/edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateFormfieldmeta(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("formfieldmeta/edit");
		String id = PageUtils.getString(PageUtils.getParameters(request).get("id"));
		mav.addObject("dto", service.getFormfieldmetaById(id));
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Formfieldmeta dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		service.saveFormfieldmeta(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> delFormfieldmeta(HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		String id = PageUtils.getString(data.get("id"));
		service.delFormfieldmetaById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
}