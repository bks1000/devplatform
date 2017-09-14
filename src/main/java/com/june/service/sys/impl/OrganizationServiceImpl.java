/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys.impl;

import com.june.dao.sys.IOrganizationDao;
import com.june.dto.sys.Organization;
import com.june.service.BaseService;
import com.june.service.sys.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrganizationServiceImpl extends BaseService implements IOrganizationService{
	
	@Autowired
	private IOrganizationDao dao;
	
	
	public List<Organization> getOrganizationList() {
		return dao.getOrganizationList();
	}
	
			
	public Organization getOrganizationById(Integer Orgid) {
		return dao.getOrganizationById(Orgid);
	}
	
	
	public void saveOrganization(Organization dto) {
		dao.saveOrganization(dto);
	}
	
			
	public void delOrganizationById(Integer Orgid) {
		dao.delOrganizationById(Orgid);
	}

	@Override
	public List<Map<String, Object>> getOrganizationTree() {
		return  dao.getOrganizationTree();
	}

}