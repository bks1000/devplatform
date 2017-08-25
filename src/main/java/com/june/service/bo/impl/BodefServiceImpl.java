/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.service.bo.impl;

import com.june.dao.bo.IBodefDao;
import com.june.dto.bo.Bodef;
import com.june.service.BaseService;
import com.june.service.bo.IBodefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodefServiceImpl extends BaseService implements IBodefService{
	
	@Autowired
	private IBodefDao dao;
	
	
	public List<Bodef> getBodefList() {
		return dao.getBodefList();
	}
	
			
	public Bodef getBodefById(String Boid) {
		return dao.getBodefById(Boid);
	}
	
	
	public void saveBodef(Bodef dto) {
		dao.saveBodef(dto);
	}
	
			
	public void delBodefById(String Boid) {
		dao.delBodefById(Boid);
	}
	
}