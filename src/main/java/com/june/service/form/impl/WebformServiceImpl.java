/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.form.impl;

import com.june.dao.form.IWebformDao;
import com.june.dto.form.Webform;
import com.june.service.BaseService;
import com.june.service.form.IWebformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WebformServiceImpl extends BaseService implements IWebformService{
	
	@Autowired
	private IWebformDao dao;
	
	
	public List<Webform> getWebformList() {
		return dao.getWebformList();
	}
	
			
	public Webform getWebformById(String Wid) {
		return dao.getWebformById(Wid);
	}
	
	
	public void saveWebform(Webform dto) {
		dao.saveWebform(dto);
	}
	
			
	public void delWebformById(String Wid) {
		dao.delWebformById(Wid);
	}

	public List<Map<String, Object>> getFormField(String boid) {
		return dao.getFormField(boid);
	}

}