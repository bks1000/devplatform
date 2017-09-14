/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import com.june.dao.BaseDao;
import com.june.dao.sys.IRolesDao;
import com.june.dto.sys.Roles;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolesDaoImpl extends BaseDao implements IRolesDao {
	


	public List<Roles> getRolesList() {
		return this.find("from Roles");
	}
	
			
	public Roles getRolesById(Integer Rid) {
		return get(Roles.class, Rid);
	}
	
	
	public void saveRoles(Roles dto) {
		//saveOrUpdate(dto);
		if(dto.getRid()==null){
			save(dto);
		}else if (exists(Roles.class,dto.getRid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delRolesById(Integer Rid) {
		executeSql("DELETE FROM Roles WHERE Rid=?", Rid);
	}
	
}