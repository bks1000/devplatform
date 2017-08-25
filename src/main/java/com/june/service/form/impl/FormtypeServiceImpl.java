/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.form.impl;

import com.june.dao.form.IFormtypeDao;
import com.june.dto.form.Formtype;
import com.june.service.BaseService;
import com.june.service.form.IFormtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormtypeServiceImpl extends BaseService implements IFormtypeService{
	
	@Autowired
	private IFormtypeDao dao;
	
	
	public List<Formtype> getFormtypeList() {
		return dao.getFormtypeList();
	}
	
			
	public Formtype getFormtypeById(Integer Tid) {
		return dao.getFormtypeById(Tid);
	}
	
	
	public void saveFormtype(Formtype dto) {
		dao.saveFormtype(dto);
	}
	
			
	public void delFormtypeById(Integer Tid) {
		dao.delFormtypeById(Tid);
	}
	
}