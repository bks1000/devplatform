/*
 * Copyright 2017 JUNE, Inc. All rights reserved.
 * Website: http://happydaily.imwork.net:18088
 */
 package com.june.dao.form;

import com.june.dto.form.Formtype;

import java.util.List;

public interface IFormtypeDao {
	
	
	public List<Formtype> getFormtypeList();
	
			
	public Formtype getFormtypeById(Integer Tid);
	
	
	public void saveFormtype(Formtype dto);
	
			
	public void delFormtypeById(Integer Tid);
	
}