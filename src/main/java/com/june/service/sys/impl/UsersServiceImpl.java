/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.sys.impl;

import com.june.dao.sys.IRolesDao;
import com.june.dao.sys.IUsersDao;
import com.june.dto.sys.Roles;
import com.june.dto.sys.Users;
import com.june.service.BaseService;
import com.june.service.sys.IUsersService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl extends BaseService implements IUsersService{
	
	@Autowired
	private IUsersDao dao;
	@Autowired
	private IRolesDao rolesDao;
	@Autowired
	private IdentityService identityService;
	
	
	public List<Users> getUsersList() {
		return dao.getUsersList();
	}
	
			
	public Users getUsersById(Integer Uid) {
		return dao.getUsersById(Uid);
	}

	@Override
	public Users getUsersByUName(String uname) {
		return dao.getUsersByUName(uname);
	}


	public void saveUsers(Users dto) {
		dao.saveUsers(dto);
		//向Activiti中同步
		//创建一个用户对象
		User user = identityService.newUser(dto.getUname());//id
		user.setFirstName(dto.getNickname());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPwd());
		identityService.saveUser(user);//保存用户到数据库
	}
	
			
	public void delUsersById(Integer Uid) {
		Users users = this.getUsersById(Uid);
		dao.delUsersById(Uid);
		//向Activiti中同步
		//User user = identityService.createUserQuery().userId(users.getUname()).singleResult();
		identityService.deleteUser(users.getUname());
	}

	@Override
	public void saveRoles(List<Map<String, Object>> lst) {
		dao.saveRoles(lst);

		//同步到Activiti
		//把用户添加到组
		for (Map<String,Object> map : lst){
			int uid=(int)map.get("uid");
			int rid=(int)map.get("rid");
			Roles roles = rolesDao.getRolesById(rid);
			Users users = dao.getUsersById(uid);
			identityService.createMembership(users.getUname(),roles.getRname());
		}
	}

}