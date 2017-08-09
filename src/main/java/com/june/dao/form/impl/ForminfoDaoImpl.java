/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form.impl;

import com.june.dao.BaseDao;
import com.june.dao.form.IForminfoDao;
import com.june.dto.form.Forminfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForminfoDaoImpl extends BaseDao implements IForminfoDao {
	


	public List<Forminfo> getForminfoList() {
		return this.find("from Forminfo");
	}
	
			
	public Forminfo getForminfoById(String Fid) {
		return get(Forminfo.class, Fid);
	}
	
	
	public void saveForminfo(Forminfo dto) {
		//saveOrUpdate(dto);
		if (exists(Forminfo.class,dto.getFid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delForminfoById(String Fid) {
		executeSql("DELETE FROM Forminfo WHERE Fid=?", Fid);
	}
	
}