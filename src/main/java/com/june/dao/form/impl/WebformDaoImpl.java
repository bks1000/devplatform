/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form.impl;

import com.june.dao.BaseDao;
import com.june.dao.form.IWebformDao;
import com.june.dto.form.Webform;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WebformDaoImpl extends BaseDao implements IWebformDao {
	


	public List<Webform> getWebformList() {
		return this.find("from Webform");
	}
	
			
	public Webform getWebformById(String Wid) {
		return get(Webform.class, Wid);
	}
	
	
	public void saveWebform(Webform dto) {
		//saveOrUpdate(dto);
		if (exists(Webform.class,dto.getWid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delWebformById(String Wid) {
		executeSql("DELETE FROM Webform WHERE Wid=?", Wid);
	}

	public List<Map<String,Object>> getFormField(String boid){
		String sql = "SELECT * FROM formfieldmeta WHERE fid = (SELECT fid FROM bodef WHERE boid=?)";
		return  querySqlListMap(sql,boid);
	}
	
}