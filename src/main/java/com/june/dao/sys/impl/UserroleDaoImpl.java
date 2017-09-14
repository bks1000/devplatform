/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import com.june.dao.BaseDao;
import com.june.dao.sys.IUserroleDao;
import com.june.dto.sys.Userrole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserroleDaoImpl extends BaseDao implements IUserroleDao {
	


	public List<Userrole> getUserroleList() {
		return this.find("from Userrole");
	}

	@Override
	public List<Userrole> getUserroleList(int Uid) {
		return this.find("from Userrole u where u.uid=?",Uid);
	}
	
	public void saveUserrole(Userrole dto) {

	}

	@Override
	public void delUserroleById(int Uid) {

	}

}