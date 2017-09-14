/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys;

import com.june.dto.sys.Menu;

import java.util.List;
import java.util.Map;

public interface IMenuService{
	
	public List<Menu> getMenuList();
	
			
	public Menu getMenuById(java.lang.String Id);
	
	
	public void saveMenu(Menu dto);
	
			
	public void delMenuById(java.lang.String Id);

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

	/**
	 * 获取菜单树
	 * @return
	 */
	public List<Map<String, Object>> getMenuTree();
}