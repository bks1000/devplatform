/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.sys.impl;

import com.june.core.utils.JdbcTemplateDao;
import com.june.dao.BaseDao;
import com.june.dao.sys.IMenuDao;
import com.june.dto.sys.Menu;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuDaoImpl extends BaseDao implements IMenuDao{
	
	public List<Menu> getMenuList() {
		return this.find("from Menu m order by m.idx asc");
	}

	public Menu getMenuById(String Id) {
		return get(Menu.class, Id);
	}
	
	
	public void saveMenu(Menu dto) {
		//save(dto);
		//saveOrUpdate(dto);
		if (exists(Menu.class,dto.getId())){
			update(dto);
		}else {
			save(dto);
		}
	}
			
	public void delMenuById(String Id) {
		executeSql("DELETE FROM Menu WHERE Id=?", Id);
	}

	public List<Map<String,Object>> getAllChildList(String parentId) {
		//return this.find("from Menu m where m.parentid=? order by m.idx",parentId);
		String sql ="SELECT * FROM menu m1 WHERE m1.parentid=:pid UNION " +
				"SELECT * FROM menu m2 WHERE m2.parentid in(SELECT m1.id FROM menu m1 WHERE m1.parentid=:pid)";
		NamedParameterJdbcTemplate njdbc = JdbcTemplateDao.getNamedParameterJdbcTemplate();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pid",parentId);
		return njdbc.queryForList(sql,params);
	}

	public List<Menu> getChildList(String parentId) {
		return this.find("from Menu m where m.parentid=? order by m.idx",parentId);
	}

	@Override
	public List<Map<String, Object>> getMenuTree() {
		String sql="SELECT id,name as text,parentid as pid FROM menu WHERE rs=1";
		return jdbcTemplate.queryForList(sql);
	}

}