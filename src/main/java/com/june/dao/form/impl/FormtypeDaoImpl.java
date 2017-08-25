/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form.impl;

import com.june.dao.BaseDao;
import com.june.dao.form.IFormtypeDao;
import com.june.dto.form.Formtype;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormtypeDaoImpl extends BaseDao implements IFormtypeDao {
	


	public List<Formtype> getFormtypeList() {
		return this.find("from Formtype");
	}
	
			
	public Formtype getFormtypeById(Integer Tid) {
		return get(Formtype.class, Tid);
	}
	
	
	public void saveFormtype(Formtype dto) {
		//saveOrUpdate(dto);
		if (exists(Formtype.class,dto.getTid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delFormtypeById(Integer Tid) {
		executeSql("DELETE FROM Formtype WHERE Tid=?", Tid);
	}
	
}