/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys;

import com.june.dto.sys.Menu;

import java.util.List;
import java.util.Map;

public interface IMenuDao {
	
	public List<Menu> getMenuList();
	
			
	public Menu getMenuById(String Id);
	
	
	public void saveMenu(Menu dto);
	
			
	public void delMenuById(String Id);

	/**
	 * 根据parentid获取全部子菜单
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getAllChildList(String parentId);

	/**
	 * 根据parentid获取模块菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> getChildList(String parentId);
}