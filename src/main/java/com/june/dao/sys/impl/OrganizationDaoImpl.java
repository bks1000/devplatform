/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import com.june.dao.BaseDao;
import com.june.dao.sys.IOrganizationDao;
import com.june.dto.sys.Organization;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrganizationDaoImpl extends BaseDao implements IOrganizationDao {
	


	public List<Organization> getOrganizationList() {
		return this.find("from Organization");
	}
	
			
	public Organization getOrganizationById(Integer Orgid) {
		return get(Organization.class, Orgid);
	}
	
	
	public void saveOrganization(Organization dto) {
		//saveOrUpdate(dto);
		if (dto.getOrgid()==null){
			save(dto);
		}else if (exists(Organization.class,dto.getOrgid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delOrganizationById(Integer Orgid) {
		executeSql("DELETE FROM Organization WHERE Orgid=?", Orgid);
	}

	@Override
	public List<Map<String, Object>> getOrganizationTree() {
		String sql="SELECT orgid as id,orgname AS text,porgid AS pid FROM organization WHERE rs =1";
		return  jdbcTemplate.queryForList(sql);
	}

}