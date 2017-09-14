/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys.impl;

import com.june.dao.sys.IUserroleDao;
import com.june.dto.sys.Userrole;
import com.june.service.BaseService;
import com.june.service.sys.IUserroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserroleServiceImpl extends BaseService implements IUserroleService{
	
	@Autowired
	private IUserroleDao dao;
	
	
	public List<Userrole> getUserroleList() {
		return dao.getUserroleList();
	}

	@Override
	public List<Userrole> getUserroleList(int Uid) {
		return null;
	}
	
	
	public void saveUserrole(Userrole dto) {
		dao.saveUserrole(dto);
	}

	@Override
	public void delUserroleById(int Uid) {

	}
	
}