/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.form;

import com.june.dto.form.Webform;

import java.util.List;
import java.util.Map;

public interface IWebformService{
	
	public List<Webform> getWebformList();
	
			
	public Webform getWebformById(String Wid);

	public Webform getWebformByFormkey(String formkey);
	
	
	public void saveWebform(Webform dto);
	
			
	public void delWebformById(String Wid);

	/**
	 * 根据业务ID获取表单字段定义
	 * @param boid
	 * @return
	 */
	public List<Map<String,Object>> getFormField(String boid);

	/**
	 * 保存业务数据到业务表
	 * @param params
	 * @return 返回业务数据ID，用于启动流程
	 */
	public String saveBusinessData(Map<String,Object> params);

	/**
	 * 保存流程实例ID到业务表
	 * @param boid 查找业务表名用
	 * @param id 业务表主键
	 * @param instanceId 流程实例ID
	 * @return
	 */
	public boolean saveBusinessInst(String boid,String id,String instanceId);
}