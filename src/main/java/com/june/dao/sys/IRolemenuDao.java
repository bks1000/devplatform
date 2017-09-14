/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys;

import com.june.dto.sys.Rolemenu;

import java.util.List;
import java.util.Map;

public interface IRolemenuDao {
	
	
	public List<Rolemenu> getRolemenuList();
	
			
	public List<Map<String,Object>> getRolemenuById(Integer Rid);
	
			
	public Rolemenu getRolemenuById(String Mid);
	
	
	public void saveRolemenu(List<Map<String,Object>> lst);
	
			
	public void delRolemenuById(Integer Rid);
	
			
	public void delRolemenuById(String Mid);
	
}