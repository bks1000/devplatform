/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 * 前台Web界面创建(将来通过formkey绑定到流程中)
 */
 package com.june.controller.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.Common;
import com.june.core.utils.JsonUtils;
import com.june.core.utils.PageUtils;
import com.june.dto.form.Webform;
import com.june.service.form.IWebformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author june email:359546407@qq.com
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/webform")
public class WebformController {
	@Autowired
	private IWebformService service;
	
	@RequestMapping("/list")
	public ModelAndView getWebformList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("form/webform/list");
		List<Webform> lst = service.getWebformList();
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
	public String addWebform(ModelMap bt) {
		
		bt.addAttribute("dto", 0);
		return "form/webform/edit";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView updateWebform(@PathVariable("id")String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("form/webform/edit");
		//int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
		try {
			mav.addObject("dto", JsonUtils.objectToJson(service.getWebformById(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Webform dto,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		if (dto.getWid()==null || "".equals(dto.getWid())){
			dto.setWid(UUID.randomUUID().toString());
		}
		service.saveWebform(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/del/{id}")
	@ResponseBody
	public Map<String, Object> delWebform(@PathVariable("id")String id,HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		//int id = Integer.parseInt(PageUtils.getString(data.get("id")));
		service.delWebformById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}

	/**
	 * 表单界面设计器
	 * @param id
	 * @param request
	 * @return
	 */
    @RequestMapping("/fdesign/{id}")
    public ModelAndView formDesign(@PathVariable("id")String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("formdesigner/formdesigner");
        //int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
        try {
            mav.addObject("dto", JsonUtils.objectToJson(service.getWebformById(id)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mav;
    }

	/**
	 * 根据业务ID获取表单字段(未考虑复杂业务，目前仅仅是一个业务对应一个实体表)
	 * @param boid
	 * @return
	 * @throws JsonProcessingException
	 */
    @RequestMapping(value = "/ffields/{boid}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getFormField(@PathVariable("boid")String boid) throws JsonProcessingException {
        List<Map<String,Object>> lst = service.getFormField(boid);
        return  JsonUtils.list2Json(lst);
    }

	/**
	 * 将表单html更新到数据库
	 * @param request
	 * @return
	 * @throws IOException
	 */
    @RequestMapping(value = "/savehtml")
	@ResponseBody
    public String saveFormHtml(HttpServletRequest request) throws IOException {

		 Map<String,Object> params =  PageUtils.getParameters(request);
		 String wid = PageUtils.getString(params.get("id"));
		 String html = PageUtils.getString(params.get("html"));
		 html = Common.htmlDecode(html);
		 Webform wform = service.getWebformById(wid);
		 wform.setFormtype("0");
		 wform.setFormhtml(html);
		 service.saveWebform(wform);
		 return "1";
	}
}