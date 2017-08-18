/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form;

import com.june.dto.form.Forminfo;

import java.util.List;

public interface IForminfoDao {
	
	
	public List<Forminfo> getForminfoList();
	
			
	public Forminfo getForminfoById(String Fid);
	
	
	public void saveForminfo(Forminfo dto);
	
			
	public void delForminfoById(String Fid);

	public void buildTable(String fid);
}