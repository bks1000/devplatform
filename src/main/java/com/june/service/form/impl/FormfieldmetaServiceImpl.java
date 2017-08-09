/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.form.impl;

import com.june.dao.form.IFormfieldmetaDao;
import com.june.dto.form.Formfieldmeta;
import com.june.service.BaseService;
import com.june.service.form.IFormfieldmetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormfieldmetaServiceImpl extends BaseService implements IFormfieldmetaService{
	
	@Autowired
	private IFormfieldmetaDao dao;
	
	
	public List<Formfieldmeta> getFormfieldmetaList() {
		return dao.getFormfieldmetaList();
	}
	
			
	public Formfieldmeta getFormfieldmetaById(String Ffid) {
		return dao.getFormfieldmetaById(Ffid);
	}

	public List<Formfieldmeta> getFormfieldmetaByFid(String fid) {
		return  dao.getFormfieldmetaByFid(fid);
	}

	public void saveFormfieldmeta(Formfieldmeta dto) {
		dao.saveFormfieldmeta(dto);
	}
	
			
	public void delFormfieldmetaById(String Ffid) {
		dao.delFormfieldmetaById(Ffid);
	}
	
}