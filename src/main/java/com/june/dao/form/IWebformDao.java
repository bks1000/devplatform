/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form;

import com.june.dto.form.Webform;

import java.util.List;
import java.util.Map;

public interface IWebformDao {
	
	
	public List<Webform> getWebformList();
	
			
	public Webform getWebformById(String Wid);
	
	
	public void saveWebform(Webform dto);
	
			
	public void delWebformById(String Wid);

	/**
	 * 根据业务ID获取表单字段定义
	 * @param boid
	 * @return
	 */
	public List<Map<String,Object>> getFormField(String boid);
}