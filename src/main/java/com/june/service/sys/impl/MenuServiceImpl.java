/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys.impl;

import com.june.dao.sys.IMenuDao;
import com.june.dto.sys.Menu;
import com.june.service.BaseService;
import com.june.service.sys.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseService implements IMenuService{
	
	@Autowired
	private IMenuDao dao;
	
	public List<Menu> getMenuList() {
		return dao.getMenuList();
	}
	
			
	public Menu getMenuById(java.lang.String Id) {
		return dao.getMenuById(Id);
	}
	
	
	public void saveMenu(Menu dto) {
		dao.saveMenu(dto);
	}
	
			
	public void delMenuById(java.lang.String Id) {
		dao.delMenuById(Id);
	}

	public List<Map<String,Object>> getAllChildList(String parentId) {
		return  dao.getAllChildList(parentId);
	}

	public List<Menu> getChildList(String parentId){return dao.getChildList(parentId);}
}