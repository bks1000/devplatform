/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import com.june.dao.BaseDao;
import com.june.dao.sys.IRolemenuDao;
import com.june.dto.sys.Rolemenu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RolemenuDaoImpl extends BaseDao implements IRolemenuDao {
	


	public List<Rolemenu> getRolemenuList() {
		return this.find("from Rolemenu");
	}
	
			
	public List<Map<String,Object>> getRolemenuById(Integer Rid) {

		String sql="SELECT mid FROM rolemenu WHERE rid=?";
		return  jdbcTemplate.queryForList(sql,Rid);
	}
	
			
	public Rolemenu getRolemenuById(String Mid) {
		return get(Rolemenu.class, Mid);
	}
	
	
	public void saveRolemenu(List<Map<String,Object>> lst) {
		int rid = (int)lst.get(0).get("rid");
		String dsql = "DELETE FROM rolemenu WHERE rid=?";
		jdbcTemplate.update(dsql,rid);

		String sql = "INSERT INTo rolemenu VALUES(?,?)";
		for (Map<String,Object> map :lst){
			jdbcTemplate.update(sql,map.get("rid"),map.get("mid"));
		}
	}
	
			
	public void delRolemenuById(Integer Rid) {
		executeSql("DELETE FROM Rolemenu WHERE Rid=?", Rid);
	}
	
			
	public void delRolemenuById(String Mid) {
		executeSql("DELETE FROM Rolemenu WHERE Mid=?", Mid);
	}
	
}