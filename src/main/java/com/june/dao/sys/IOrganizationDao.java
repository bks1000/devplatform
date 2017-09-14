/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys;

import com.june.dto.sys.Organization;

import java.util.List;
import java.util.Map;

public interface IOrganizationDao {
	
	
	public List<Organization> getOrganizationList();
	
			
	public Organization getOrganizationById(Integer Orgid);
	
	
	public void saveOrganization(Organization dto);
	
			
	public void delOrganizationById(Integer Orgid);

	/**
	 * 获取机构树
	 * @return
	 */
	public List<Map<String,Object>> getOrganizationTree();
	
}