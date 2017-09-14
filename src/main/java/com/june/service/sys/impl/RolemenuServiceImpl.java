/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys.impl;

import com.june.dao.sys.IRolemenuDao;
import com.june.dto.sys.Rolemenu;
import com.june.service.BaseService;
import com.june.service.sys.IRolemenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RolemenuServiceImpl extends BaseService implements IRolemenuService{
	
	@Autowired
	private IRolemenuDao dao;
	
	
	public List<Rolemenu> getRolemenuList() {
		return dao.getRolemenuList();
	}
	
			
	public List<Map<String,Object>> getRolemenuById(Integer Rid) {
		return dao.getRolemenuById(Rid);
	}
	
			
	public Rolemenu getRolemenuById(String Mid) {
		return dao.getRolemenuById(Mid);
	}

	@Override
	public void saveRolemenu(List<Map<String,Object>> lst) {
		dao.saveRolemenu(lst);
	}

	public void delRolemenuById(Integer Rid) {
		dao.delRolemenuById(Rid);
	}
	
			
	public void delRolemenuById(String Mid) {
		dao.delRolemenuById(Mid);
	}
	
}