/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form.impl;

import com.june.dao.BaseDao;
import com.june.dao.form.IWebformDao;
import com.june.dto.form.Webform;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class WebformDaoImpl extends BaseDao implements IWebformDao {
	


	public List<Webform> getWebformList() {
		return this.find("from Webform");
	}
	
			
	public Webform getWebformById(String Wid) {
		return get(Webform.class, Wid);
	}

	@Override
	public Webform getWebformByFormkey(String formkey) {
		return ((List<Webform>)this.find("from Webform w WHERE w.formkey=?",formkey)).get(0);
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

	@Override
	public String saveBusinessData(Map<String, Object> params) {
		//获取业务表 表名
		String tablename = "c_";
		String sql = "SELECT fname FROM bodef WHERE boid=?";
		tablename = tablename + jdbcTemplate.queryForObject(sql,String.class,params.get("boid").toString());
		//创建主键
		String uuid = UUID.randomUUID().toString();
		//移除boid
		params.remove("boid");
		//循环参数
		StringBuilder columns = new StringBuilder();
		StringBuilder data = new StringBuilder();
		ArrayList array = new ArrayList();
		columns.append("id,");
		//data.append(uuid+",");
		data.append("?,");
		array.add(uuid);
		for (Map.Entry<String,Object> entry : params.entrySet()) {
			columns.append(entry.getKey());
			columns.append(",");

			data.append("?,");
			array.add(entry.getValue());
		}
		columns.deleteCharAt(columns.length()-1);
		data.deleteCharAt(data.length()-1);

		sql = String.format("insert into %s(%s) values(%s)",tablename,columns.toString(),data.toString());
		jdbcTemplate.update(sql,array.toArray());
		return uuid;
	}

	@Override
	public boolean saveBusinessInst(String boid,String id, String instanceId) {
		//获取业务表 表名
		String tablename = "c_";
		String sql = "SELECT fname FROM bodef WHERE boid=?";
		tablename = tablename + jdbcTemplate.queryForObject(sql,String.class,boid);
		sql = String.format("UPDATE %s SET proc_inst_id=? WHERE id=?",tablename);
		return jdbcTemplate.update(sql,instanceId,id)>0;
	}

}