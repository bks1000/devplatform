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

	@Override
	public Webform getWebformByFormkey(String formkey) {
		return dao.getWebformByFormkey(formkey);
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

	@Override
	public String saveBusinessData(Map<String, Object> params) {
		return dao.saveBusinessData(params);
	}
	/**
	 * 保存流程实例ID到业务表
	 * @param boid 查找业务表名用
	 * @param id 业务表主键
	 * @param instanceId 流程实例ID
	 * @return
	 */
	public boolean saveBusinessInst(String boid,String id,String instanceId){
		return  dao.saveBusinessInst(boid,id,instanceId);
	}
}