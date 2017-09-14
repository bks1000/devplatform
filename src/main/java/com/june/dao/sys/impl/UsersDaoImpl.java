/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import com.june.dao.BaseDao;
import com.june.dao.sys.IUsersDao;
import com.june.dto.sys.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UsersDaoImpl extends BaseDao implements IUsersDao {
	


	public List<Users> getUsersList() {
		return this.find("from Users");
	}
	
			
	public Users getUsersById(Integer Uid) {
		return get(Users.class, Uid);
	}

	@Override
	public Users getUsersByUName(String uname) {
		String hql = "from Users u where u.uname=?";
		return (Users) findObject(hql,uname);
	}


	public void saveUsers(Users dto) {
		//saveOrUpdate(dto);
		if (dto.getUid()==null){
			save(dto);
		}else if (exists(Users.class,dto.getUid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delUsersById(Integer Uid) {
		executeSql("DELETE FROM Users WHERE Uid=?", Uid);
		String sql = "DELETE FROM userrole WHERE uid=?";
		jdbcTemplate.update(sql,Uid);
	}

	@Override
	public void saveRoles(List<Map<String, Object>> lst) {
		if (lst.size()>0){
			String sql="DELETE FROM userrole WHERE uid=?";
			jdbcTemplate.update(sql,lst.get(0).get("uid"));
		}
		String sql="INSERT into userrole VALUES(?,?)";
		for (Map<String,Object> map:lst) {
			jdbcTemplate.update(sql,map.get("uid"),map.get("rid"));
		}
	}

}