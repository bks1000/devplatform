/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import java.util.List;

import com.june.dao.BaseDao;
import com.june.dao.sys.IMenuDao;
import com.june.dto.sys.Menu;

import org.springframework.stereotype.Component;

@Component
public class MenuDaoImpl extends BaseDao implements IMenuDao{
	
	public List<Menu> getMenuList() {
		return this.find("from Menu");
	}

	public Menu getMenuById(String Id) {
		return get(Menu.class, Id);
	}
	
	
	public void saveMenu(Menu dto) {
		saveOrUpdate(dto);
	}
	
			
	public void delMenuById(String Id) {
		executeSql("DELETE FROM Menu WHERE Id=?", Id);
	}

	public List<Menu> getChildList(String parentId) {
		return this.find("from Menu where Menu.parentid=?",parentId);
	}

}