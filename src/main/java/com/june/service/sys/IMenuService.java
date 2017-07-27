/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys;

import java.util.List;


import com.june.dto.sys.Menu;

public interface IMenuService{
	
	public List<Menu> getMenuList();
	
			
	public Menu getMenuById(java.lang.String Id);
	
	
	public void saveMenu(Menu dto);
	
			
	public void delMenuById(java.lang.String Id);

	/**
	 * 根据parentid获取子菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> getChildList(String parentId);
}