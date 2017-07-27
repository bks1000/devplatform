/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys;

import java.util.List;


import com.june.dto.sys.Menu;

public interface IMenuDao {
	
	public List<Menu> getMenuList();
	
			
	public Menu getMenuById(String Id);
	
	
	public void saveMenu(Menu dto);
	
			
	public void delMenuById(String Id);

	/**
	 * 根据parentid获取子菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> getChildList(String parentId);
	
}