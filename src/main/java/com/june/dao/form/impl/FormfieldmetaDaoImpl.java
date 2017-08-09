/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form.impl;

import com.june.dao.BaseDao;
import com.june.dao.form.IFormfieldmetaDao;
import com.june.dto.form.Formfieldmeta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormfieldmetaDaoImpl extends BaseDao implements IFormfieldmetaDao {
	


	public List<Formfieldmeta> getFormfieldmetaList() {
		return this.find("from Formfieldmeta");
	}
	
			
	public Formfieldmeta getFormfieldmetaById(String Ffid) {
		return get(Formfieldmeta.class, Ffid);
	}

	public List<Formfieldmeta> getFormfieldmetaByFid(String fid) {
		String hql = "from Formfieldmeta ff where ff.fid=?";
		return find(hql,fid);
	}

	public void saveFormfieldmeta(Formfieldmeta dto) {
		//saveOrUpdate(dto);
		if (exists(Formfieldmeta.class,dto.getFfid())){
			update(dto);
		}else {
			save(dto);
		}
	}
	
			
	public void delFormfieldmetaById(String Ffid) {
		executeSql("DELETE FROM Formfieldmeta WHERE Ffid=?", Ffid);
	}
	
}