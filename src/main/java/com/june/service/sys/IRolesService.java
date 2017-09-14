/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys;

import com.june.dto.sys.Roles;

import java.util.List;

public interface IRolesService{
	
	public List<Roles> getRolesList();
	
			
	public Roles getRolesById(Integer Rid);
	
	
	public void saveRoles(Roles dto);
	
			
	public void delRolesById(Integer Rid);
	
}