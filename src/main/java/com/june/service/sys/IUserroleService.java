/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys;

import com.june.dto.sys.Userrole;

import java.util.List;

public interface IUserroleService{

	public List<Userrole> getUserroleList();


	public List<Userrole> getUserroleList(int Uid);


	public void saveUserrole(Userrole dto);


	public void delUserroleById(int Uid);
	
}