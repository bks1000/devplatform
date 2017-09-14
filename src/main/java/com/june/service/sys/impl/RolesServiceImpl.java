/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys.impl;

import com.june.dao.sys.IRolesDao;
import com.june.dto.sys.Roles;
import com.june.service.BaseService;
import com.june.service.sys.IRolesService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl extends BaseService implements IRolesService{
	
	@Autowired
	private IRolesDao dao;
	@Autowired
	IdentityService identityService;

	public List<Roles> getRolesList() {
		return dao.getRolesList();
	}
			
	public Roles getRolesById(Integer Rid) {
		return dao.getRolesById(Rid);
	}
	
	
	public void saveRoles(Roles dto) {
		dao.saveRoles(dto);

		//同步到Activiti
		//创建一个组对象
		Group group = identityService.newGroup(dto.getRname());
		group.setName(dto.getRname());
		group.setType("assignment");
		identityService.saveGroup(group);//保存组
	}
	
			
	public void delRolesById(Integer Rid) {
		Roles role = this.getRolesById(Rid);
		dao.delRolesById(Rid);
		//同步到Activiti
		identityService.deleteGroup(role.getRname());
	}
	
}