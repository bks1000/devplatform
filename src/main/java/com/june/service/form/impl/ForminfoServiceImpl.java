/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.form.impl;

import com.june.dao.form.IFormfieldmetaDao;
import com.june.dao.form.IForminfoDao;
import com.june.dto.form.Formfieldmeta;
import com.june.dto.form.Forminfo;
import com.june.service.BaseService;
import com.june.service.form.IForminfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForminfoServiceImpl extends BaseService implements IForminfoService{
	
	@Autowired
	private IForminfoDao dao;

	@Autowired
	private IFormfieldmetaDao ffdao;
	
	
	public List<Forminfo> getForminfoList() {
		return dao.getForminfoList();
	}
	
			
	public Forminfo getForminfoById(String Fid) {
		return dao.getForminfoById(Fid);
	}
	
	
	public void saveForminfo(Forminfo dto, List<Formfieldmeta> lst) {
		dao.saveForminfo(dto);
		for(Formfieldmeta ff :lst){
			ffdao.saveFormfieldmeta(ff);
		}
	}
			
	public void delForminfoById(String Fid) {
		dao.delForminfoById(Fid);
	}
	
}