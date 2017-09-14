/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys;

import com.june.dto.sys.Users;

import java.util.List;
import java.util.Map;

public interface IUsersService{
	
	public List<Users> getUsersList();
	
			
	public Users getUsersById(Integer Uid);

	public Users getUsersByUName(String uname);
	
	
	public void saveUsers(Users dto);
	
			
	public void delUsersById(Integer Uid);

	public void saveRoles(List<Map<String,Object>> lst);
}