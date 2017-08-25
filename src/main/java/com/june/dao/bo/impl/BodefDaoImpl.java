/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.bo.impl;

import com.june.dao.BaseDao;
import com.june.dao.bo.IBodefDao;
import com.june.dto.bo.Bodef;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BodefDaoImpl extends BaseDao implements IBodefDao {
	


	public List<Bodef> getBodefList() {
		return this.find("from Bodef");
	}
	
			
	public Bodef getBodefById(String Boid) {
		return get(Bodef.class, Boid);
	}
	
	
	public void saveBodef(Bodef dto) {
		//saveOrUpdate(dto);
		if (exists(Bodef.class,dto.getBoid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delBodefById(String Boid) {
		executeSql("DELETE FROM Bodef WHERE Boid=?", Boid);
	}
	
}